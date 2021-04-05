package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Picture;

public interface PicturesDAO extends JpaRepository<Picture, Long>{

}
