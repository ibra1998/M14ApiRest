package com.example.demo.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.dao.PicturesDAO;
import com.example.demo.dao.ShopsDAO;
import com.example.demo.models.Picture;
import com.example.demo.models.Shop;
import com.sun.tools.javac.code.Attribute.Array;

@Controller
@EnableWebMvc
@RequestMapping("/shops")
public class ControllerRest {
	@Autowired
	private ShopsDAO shopsDAO;
	
	@GetMapping("/home/")
	public ArrayList<ResponseEntity<String>> goindex(){
		ArrayList<ResponseEntity<String>> page=new ArrayList<ResponseEntity<String>> ();
		page.add(ResponseEntity.ok("index"));
		page.add(ResponseEntity.ok("main.js"));
		page.add(ResponseEntity.ok("styles.css"));
		
		return page;
	}
	@GetMapping("/homes/")
	public String getHtml(Model modelo){
		String page= "pages/index";
		
		return page;
	}
	@GetMapping("/homes/estilos.css")
	public String getstyles() {
		return "pages/estilos.css";
	}
	@GetMapping("homes/main.js")
	public String getScripts() {
		return "pages/main.js";
						
	}
	@GetMapping("/")	
	public ResponseEntity<List <Shop>> getShop(){
		List<Shop> shops = shopsDAO.findAll();
		return ResponseEntity.ok(shops);
	}
	
	@RequestMapping(value="{shopId}")
	public ResponseEntity<Shop> getShopById(@PathVariable ("shopId") Long shopId){
		Optional<Shop> optionalShop = shopsDAO.findById(shopId);
		if(optionalShop.isPresent()) {
			return ResponseEntity.ok(optionalShop.get());
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PostMapping("/")
	public ResponseEntity<Shop> postShop(@RequestBody Shop shop){
		Shop newShop = shopsDAO.save(shop);
		return ResponseEntity.ok(newShop);
	}
	
	@PostMapping("/{id}/pictures")
	public ResponseEntity <Picture> postShop(@RequestBody Picture picture, @PathVariable Long id){
		Optional<Shop> myOptionalshop = shopsDAO.findById(id);
		if(myOptionalshop.isPresent() && ! (picture.getPrice()==0.0) ) {
			Shop myshop = myOptionalshop.get();
			Collection<Picture> pictures = myshop.getPicture();
			pictures.add(picture);
			myshop.setPicture(pictures);
			shopsDAO.save(myshop);
			return ResponseEntity.ok(picture);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}/pictures")
	public ResponseEntity <Collection <Picture>> getShopsPictures(@PathVariable("id") Long shopId){
		Optional<Shop> myOptionalShop = shopsDAO.findById(shopId);
		if (myOptionalShop.isPresent()) {
			Shop shop = myOptionalShop.get();
			Collection<Picture> myPictures = shop.getPicture();
			return ResponseEntity.ok(myPictures);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@DeleteMapping("/{id}/pictures")
	public ResponseEntity<Shop> deleteShopPictures(@PathVariable("id") Long shopId){
		Optional<Shop> myOptionalshop = shopsDAO.findById(shopId);
		if(myOptionalshop.isPresent()) {
			Shop myshop = myOptionalshop.get();
			Collection<Picture> pictures = myshop.getPicture();
			pictures.removeAll(pictures);
			myshop.setPicture(pictures);
			shopsDAO.save(myshop);
			return ResponseEntity.ok(myshop);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	

}
