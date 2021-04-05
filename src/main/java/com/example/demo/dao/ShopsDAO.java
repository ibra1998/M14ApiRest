package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Shop;

public interface ShopsDAO extends JpaRepository<Shop, Long>{

}
