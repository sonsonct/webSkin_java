package com.WebSkin.demo.models;

public class CartProductDTO {
	private int cartId;
    private int productId;
    private String productName;
    private String imgMain;
    private int price;
    private int quantity;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImgMain() {
		return imgMain;
	}
	public void setImgMain(String imgMain) {
		this.imgMain = imgMain;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartProductDTO(int cartId, int productId, String productName, String imgMain, int price, int quantity) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.productName = productName;
		this.imgMain = imgMain;
		this.price = price;
		this.quantity = quantity;
	}
	public CartProductDTO() {
		super();
	}
    
}
