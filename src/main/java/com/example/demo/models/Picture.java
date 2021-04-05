package com.example.demo.models;
import java.sql.Date;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Required;

import com.sun.istack.NotNull;


@Entity
public class Picture {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="author")
	private String author = "anonymous";

	@Column(name="price", nullable=false)
	private double price;
	@Column(name="CREATED_DATE", updatable = false)
	private java.sql.Timestamp createdDate = new java.sql.Timestamp(System.currentTimeMillis());

	
	
	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.sql.Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	

}
