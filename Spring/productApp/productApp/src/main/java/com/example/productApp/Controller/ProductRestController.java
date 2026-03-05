package com.example.productApp.Controller;

import com.example.productApp.Model.Product;
import com.example.productApp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return service.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    @PostMapping
    public  String addProduct(@RequestBody Product product){
        service.saveProduct(product);
        return "Product Added Successfully";
    }
    @PostMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return " Product Deleted";
    }






}
