package com.shoppersworld.Inventory.service;

import com.shoppersworld.Inventory.dto.InventoryResponse;
import com.shoppersworld.Inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository irs;
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isproductinstock(List<String> Product){
       return irs.findByProductIn(Product).stream().map(inventory->InventoryResponse.builder()
               .product(inventory.getProduct())
               .instock(inventory.getQuantity()>0 && inventory.getProduct()!= null)
               .build()
       ).toList();
    }


}
