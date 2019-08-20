package com.maharjanewj.springapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maharjanewj.springapi.models.Products;
import com.maharjanewj.springapi.repositories.ProductRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public Iterable<Products> product() {
        return productRepository.findAll();
    }
//    @RequestMapping(method = RequestMethod.GET, value = "/products")
//    public Products product() {
//        System.out.println("product requested");
//        Products products = new Products();
//        products.setId("1");
//        products.setProdDesc("this is it");
//        products.setProdImage("mewo");
//        products.setProdName("P1");
//        products.setProdPrice(10.0);
//        return products;
//    }



}
