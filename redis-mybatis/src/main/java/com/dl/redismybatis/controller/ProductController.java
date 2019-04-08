package com.dl.redismybatis.controller;

import com.dl.redismybatis.entity.Product;
import com.dl.redismybatis.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public Product getProductInfo(@PathVariable("id") Long productId){
        return productMapper.select(productId);
    }


    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Product updateProductInfo(@RequestBody Product newProduct){
        Product product = productMapper.select(1);
        if (product == null) {
            throw new ProductNotFoundException(1);
        }
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        productMapper.update(product);
        return product;
    }
}
