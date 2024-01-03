package com.WebSkin.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WebSkin.demo.Repository.CartRepository;
import com.WebSkin.demo.Repository.ProductRepository;
import com.WebSkin.demo.Repository.VnpayRepository;
import com.WebSkin.demo.models.Cart;
import com.WebSkin.demo.models.CartProductDTO;
import com.WebSkin.demo.models.Product;
import com.WebSkin.demo.models.ResponseOBJ;
import com.WebSkin.demo.models.Vnpay;

import VnpConfig.VnpService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/v1/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
	@Autowired
	private CartRepository cartrp;
	@Autowired
	private ProductRepository prdrp;
	@Autowired
	private VnpayRepository vnpayrp;
	
//	@GetMapping("/hello")
//	public String hello() {
//		
//		return "helooo";
//	}
	@PostMapping("/")
	public ResponseEntity<ResponseOBJ> getCart(@RequestBody Map<String, String> request) {
        int idcustomer = Integer.valueOf(request.get("idcustomer"));
        
        
        List<Cart> carts = cartrp.findByIdCustumer(idcustomer);
        List<CartProductDTO> cartProductDTOs = new ArrayList<>();
        if (!carts.isEmpty()) {
        	for (Cart cart : carts) {
                Product cprd = prdrp.findById(cart.getIdProduct());
                if (cprd != null) {
                    CartProductDTO dto = new CartProductDTO();
                    dto.setCartId(cart.getId());
                    dto.setProductId(cprd.getId());
                    dto.setProductName(cprd.getProductName());
                    dto.setImgMain(cprd.getImg_main());
                    dto.setPrice(cprd.getPrice());
                    dto.setQuantity(cart.getQuantity());
                    cartProductDTOs.add(dto);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseOBJ("true", "Tìm thấy thành công", cartProductDTOs)
            );
        }else {
        	return ResponseEntity.status(HttpStatus.OK).body(
        		new ResponseOBJ("false", "Không tìm thấy hoặc dữ liệu không hợp lệ", null)
            );
		}
        
        
        
    }
	@PutMapping("/updateQuantity")
	public ResponseEntity<ResponseOBJ> updateqt(@RequestBody Map<String, String> request){
		int id = Integer.valueOf(request.get("id"));
		int quantity = Integer.valueOf(request.get("quantity"));
		Cart cart = cartrp.findById(id).orElse(null);
		if(cart !=null) {
			cart.setQuantity(quantity);
			cartrp.save(cart);
			return ResponseEntity.status(HttpStatus.OK).body(
	                new ResponseOBJ("true", "update sl thành công","")
	        );
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
	                new ResponseOBJ("true", "update sl thất bại","")
	        );
		}
	}
	@DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<ResponseOBJ> deletecart(@PathVariable Integer id ) {
        try {            
            if (cartrp.existsById(id)) {
                cartrp.deleteById(id);
                return ResponseEntity.ok(new ResponseOBJ("true", "Xóa sản phẩm khỏi giỏ hàng thành công", ""));
            } else {
                return ResponseEntity.ok(new ResponseOBJ("false", "Sản phẩm không tồn tại trong giỏ hàng", ""));
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(new ResponseOBJ("false", "Lỗi định dạng dữ liệu", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseOBJ("false", "Lỗi khi xóa sản phẩm khỏi giỏ hàng", ""));
        }
    }
	@PostMapping("/addToCart")
	public ResponseEntity<ResponseOBJ> addToCart(@RequestBody Cart cart) {
	    try {
	        synchronized (this) { // Synchronize to ensure atomicity
	            Cart existingCartItem = cartrp.findByIdProductAndIdCustumer(cart.getIdProduct(), cart.getIdCustumer());

	            if (existingCartItem != null) {
	                existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
	                cartrp.save(existingCartItem);
	                return ResponseEntity.ok(new ResponseOBJ("true", "Sản phẩm đã tồn tại trong giỏ hàng +1", existingCartItem));
	            } else {
	                cartrp.save(cart);
	                return ResponseEntity.ok(new ResponseOBJ("true", "Thêm thành công", cart));
	            }
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseOBJ("false", "Lỗi xử lý", null));
	    }
	}
	@PostMapping("/submitOrder")
    public String submidOrder(@RequestBody Map<String, String> rq,HttpServletRequest request){
		VnpService sv = new VnpService();
		int orderTotal =  Integer.valueOf(rq.get("amount"));
		String orderInfo =  rq.get("orderInfo");
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = sv.createOrder(orderTotal, orderInfo, baseUrl);
        return vnpayUrl;
    }
	@PostMapping("/addvnpay")
    public ResponseEntity<ResponseOBJ> GetMapping(@RequestBody Vnpay vnpay){
		try {
			vnpayrp.save(vnpay);
			return ResponseEntity.status(HttpStatus.OK).body(
					
	                new ResponseOBJ("true", "lưu thanh cong",vnpay)
	        );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(
	                new ResponseOBJ("false", "lưu that bai thất bại","")
	        );
		}
        
        
			
		
			
		
    }
	
}
