package com.example.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;


import org.hibernate.annotations.Cascade;

@Entity
public class Shop {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="name", nullable = false, length=30)
	private String name;
	@Column(name="capacity", nullable = false)
	private int capacity;
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Picture> picture;


	public Collection<Picture> getPicture() {
		return this.picture;
	}

	public void setPicture(Collection<Picture>  picture) {
		this.picture = picture;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
