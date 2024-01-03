package com.WebSkin.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vn_pay")
public class Vnpay {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_vnpay;
	private String code_cart; 
	private String vnp_amount;
	private String vnp_bankcode;
	private String vnp_banktranno; 
	private String vnp_cardtype; 
	private String vnp_orderinfo;
	private String vnp_paydate;
	private String vnp_tmncode;
	private String vnp_transactionno;
	public int getId_vnpay() {
		return id_vnpay;
	}
	public void setId_vnpay(int id_vnpay) {
		this.id_vnpay = id_vnpay;
	}
	public String getCode_cart() {
		return code_cart;
	}
	public void setCode_cart(String code_cart) {
		this.code_cart = code_cart;
	}
	public String getVnp_amount() {
		return vnp_amount;
	}
	public void setVnp_amount(String vnp_amount) {
		this.vnp_amount = vnp_amount;
	}
	public String getVnp_bankcode() {
		return vnp_bankcode;
	}
	public void setVnp_bankcode(String vnp_bankcode) {
		this.vnp_bankcode = vnp_bankcode;
	}
	public String getVnp_banktranno() {
		return vnp_banktranno;
	}
	public void setVnp_banktranno(String vnp_banktranno) {
		this.vnp_banktranno = vnp_banktranno;
	}
	public String getVnp_cardtype() {
		return vnp_cardtype;
	}
	public void setVnp_cardtype(String vnp_cardtype) {
		this.vnp_cardtype = vnp_cardtype;
	}
	public String getVnp_orderinfo() {
		return vnp_orderinfo;
	}
	public void setVnp_orderinfo(String vnp_orderinfo) {
		this.vnp_orderinfo = vnp_orderinfo;
	}
	public String getVnp_paydate() {
		return vnp_paydate;
	}
	public void setVnp_paydate(String vnp_paydate) {
		this.vnp_paydate = vnp_paydate;
	}
	public String getVnp_tmncode() {
		return vnp_tmncode;
	}
	public void setVnp_tmncode(String vnp_tmncode) {
		this.vnp_tmncode = vnp_tmncode;
	}
	public String getVnp_transactionno() {
		return vnp_transactionno;
	}
	public void setVnp_transactionno(String vnp_transactionno) {
		this.vnp_transactionno = vnp_transactionno;
	}
	public Vnpay(String code_cart, String vnp_amount, String vnp_bankcode, String vnp_banktranno,
			String vnp_cardtype, String vnp_orderinfo, String vnp_paydate, String vnp_tmncode,
			String vnp_transactionno) {
		this.code_cart = code_cart;
		this.vnp_amount = vnp_amount;
		this.vnp_bankcode = vnp_bankcode;
		this.vnp_banktranno = vnp_banktranno;
		this.vnp_cardtype = vnp_cardtype;
		this.vnp_orderinfo = vnp_orderinfo;
		this.vnp_paydate = vnp_paydate;
		this.vnp_tmncode = vnp_tmncode;
		this.vnp_transactionno = vnp_transactionno;
	}
	public Vnpay() {
		
	}

}
