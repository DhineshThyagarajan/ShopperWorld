package com.shoppersworld.OrderService.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

private List<OrderItemRequest>  orderlist;
}
