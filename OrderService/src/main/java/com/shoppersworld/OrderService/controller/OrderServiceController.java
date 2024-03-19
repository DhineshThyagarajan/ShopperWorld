package com.shoppersworld.OrderService.controller;

import com.shoppersworld.OrderService.dto.OrderRequest;
import com.shoppersworld.OrderService.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderServiceController {

    private final OrderService ors;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory")
    @Retry(name="inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest ordr){
       return CompletableFuture.supplyAsync(()->ors.placeorder(ordr));

    }
    public CompletableFuture<String> fallbackMethod(OrderRequest ord,RuntimeException rt){
        return CompletableFuture.supplyAsync(()-> "Server is down try again later");

    }
}
