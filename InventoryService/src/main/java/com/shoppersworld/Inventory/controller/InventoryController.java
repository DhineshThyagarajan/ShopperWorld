package com.shoppersworld.Inventory.controller;

import com.shoppersworld.Inventory.dto.InventoryResponse;
import com.shoppersworld.Inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService ivs;
    @GetMapping
    public List<InventoryResponse> isinstock(@RequestParam List<String> product){
        List<InventoryResponse> res=ivs.isproductinstock(product);
        System.out.print("Hello");
        return  res;

    }




}
