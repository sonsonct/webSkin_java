package com.WebSkin.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "id_product")
	private int idProduct;
	@Column(name = "id_custumer")
	private int idCustumer;
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getIdCustumer() {
		return idCustumer;
	}
	public void setIdCustumer(int idCustumer) {
		this.idCustumer = idCustumer;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Cart(int id, int idProduct, int idCustumer, int quantity) {
		super();
		this.id = id;
		this.idProduct = idProduct;
		this.idCustumer = idCustumer;
		this.quantity = quantity;
	}
	public Cart() {
		super();
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", idProduct=" + idProduct + ", idCustumer=" + idCustumer + ", quantity=" + quantity
				+ "]";
	}
	
}
