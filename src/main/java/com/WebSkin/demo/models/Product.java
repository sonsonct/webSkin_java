package com.WebSkin.demo.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "product_name")
    private String productName; 
    private String img_main;
    private String img_extra;
    private String img_extra1;
    private String img_extra2;
    private int quantity; 
    private int price; 
    private String type; 
    private String describe; 
    @Column(name = "category_id")
    private int categoryid; 
    private Date product_date;
    public Product() {

    }
    public Product(int id, String productName, String img_main, String img_extra, String img_extra1, String img_extra2,
                    int quantity, int price, String type, String describe, int categoryid, Date product_date) {

            this.id = id;
            this.productName = productName;
            this.img_main = img_main;
            this.img_extra = img_extra;
            this.img_extra1 = img_extra1;
            this.img_extra2 = img_extra2;
            this.quantity = quantity;
            this.price = price;
            this.type = type;
            this.describe = describe;
            this.categoryid = categoryid;
            this.product_date = product_date;
    }
    public int getId() {
            return id;
    }
    public void setId(int id) {
            this.id = id;
    }
    public String getProductName() {
            return productName;
    }
    public void setProductName(String product_name) {
            this.productName = product_name;
    }
    public String getImg_main() {
            return img_main;
    }
    public void setImg_main(String img_main) {
            this.img_main = img_main;
    }
    public String getImg_extra() {
            return img_extra;
    }
    public void setImg_extra(String img_extra) {
            this.img_extra = img_extra;
    }
    public String getImg_extra1() {
            return img_extra1;
    }
    public void setImg_extra1(String img_extra1) {
            this.img_extra1 = img_extra1;
    }
    public String getImg_extra2() {
            return img_extra2;
    }
    public void setImg_extra2(String img_extra2) {
            this.img_extra2 = img_extra2;
    }
    public int getQuantity() {
            return quantity;
    }
    public void setQuantity(int quantity) {
            this.quantity = quantity;
    }
    public int getPrice() {
            return price;
    }
    public void setPrice(int price) {
            this.price = price;
    }
    public String getType() {
            return type;
    }
    public void setType(String type) {
            this.type = type;
    }
    public String getDescribe() {
            return describe;
    }
    public void setDescribe(String describe) {
            this.describe = describe;
    }
    public int getCategoryid() {
            return categoryid;
    }
    public void setCategoryid(int categoryid) {
            this.categoryid = categoryid;
    }
    public Date getProduct_date() {
            return product_date;
    }
    public void setProduct_date(Date product_date) {
            this.product_date = product_date;
    }
    @Override
    public String toString() {
            return "Product [id=" + id + ", product_name=" + productName + ", img_main=" + img_main + ", img_extra="
                            + img_extra + ", img_extra1=" + img_extra1 + ", img_extra2=" + img_extra2 + ", quantity=" + quantity
                            + ", price=" + price + ", type=" + type + ", describe=" + describe + ", category_id=" + categoryid
                            + ", product_date=" + product_date + "]";
    }
}
