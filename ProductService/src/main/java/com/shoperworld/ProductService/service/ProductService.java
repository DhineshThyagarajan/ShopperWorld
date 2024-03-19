package com.shoperworld.ProductService.service;
import com.shoperworld.ProductService.Repository.ProductRepository;
import com.shoperworld.ProductService.dto.ProductRequest;
import com.shoperworld.ProductService.dto.ProductResponse;
import com.shoperworld.ProductService.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repo;
    public void  CreateProduct(ProductRequest pr){
        Product  pb = Product.builder()
                .name(pr.getName())
                .description(pr.getDescription())
                .Price(pr.getPrice())
                .build();
        repo.save(pb);
        log.info("Product with id:{} is saved ",pb.getId());
    }
    public List<ProductResponse> GetallProduct(){

        List<Product> ps = repo.findAll();
        return ps.stream().map(this::Maptoproductresponse).toList();

    }
    public ProductResponse Maptoproductresponse(Product p){

             return ProductResponse.builder()
                                  .Id(p.getId()).name(p.getName()).description(p.getDescription()).Price(p.getPrice()).build();


    }

}
