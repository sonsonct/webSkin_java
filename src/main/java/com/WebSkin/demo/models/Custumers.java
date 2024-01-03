package com.WebSkin.demo.models;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "custumers")
public class Custumers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String name;
    @Column(nullable = false, unique = true, length = 255)
    private String email;
    private String phone_number;
    private String address;
    private String password;
    private int verify_status;

    public Custumers() {
    }

    public Custumers(String name, String email, String phone_number, String address, String password, int verify_status) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
        this.verify_status = verify_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(int verify_status) {
        this.verify_status = verify_status;
    }

    @Override
    public String toString() {
        return "Custumers{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone_number=" + phone_number + ", address=" + address + ", password=" + password + ", verify_status=" + verify_status + '}';
    }
}
