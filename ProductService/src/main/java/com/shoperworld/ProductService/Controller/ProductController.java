package com.shoperworld.ProductService.Controller;

import com.shoperworld.ProductService.dto.ProductRequest;
import com.shoperworld.ProductService.dto.ProductResponse;
import com.shoperworld.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService ps;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateProduct(@RequestBody ProductRequest pr){
           ps.CreateProduct(pr);
           log.info("Product is SuccessFully Created");

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
       return ps.GetallProduct();

    }


}
