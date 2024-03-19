package com.shoppersworld.OrderService.service;

import com.shoppersworld.OrderService.dto.InventoryResponse;
import com.shoppersworld.OrderService.dto.OrderItemRequest;
import com.shoppersworld.OrderService.dto.OrderRequest;
import com.shoppersworld.OrderService.event.OrderPlacedevent;
import com.shoppersworld.OrderService.model.Order;
import com.shoppersworld.OrderService.model.OrderItems;
import com.shoppersworld.OrderService.repository.OrderServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import javax.management.Notification;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderServiceRepository osr;
    private final WebClient.Builder wbld;
    private final KafkaTemplate<String, OrderPlacedevent> kaft;
    @Transactional
    public String  placeorder(OrderRequest oreq){

        Order ord= new Order();
        ord.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItems> orditem= oreq.getOrderlist().stream().map(this::maptoOrderitems).toList();
        ord.setItems(orditem);
        List<String> products= ord.getItems().stream().map(OrderItems::getProduct).toList();
      InventoryResponse[] ivr=  wbld.build().get()
                .uri("http://inventory-service/api/inventory", uribuilder->uribuilder.queryParam("product",products).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
              .block();

      boolean ifinstock= Arrays.stream(ivr).allMatch(InventoryResponse::getInstock);
      if(ifinstock && ivr.length!=0) {

          osr.save(ord);
          kaft.send("notificationTopic",new OrderPlacedevent(ord.getOrderNumber()));

          return "OrderPlace successfully";
      }
      else if(ivr.length == 0){
              return "Please select a valid product";
      }
      return" Product is not in Stock";

    }
    public OrderItems maptoOrderitems(OrderItemRequest orderitemreq){
        OrderItems orderitem= new OrderItems();
        orderitem.setId(orderitemreq.getId());
        orderitem.setPrice(orderitemreq.getPrice());
        orderitem.setProduct(orderitemreq.getProduct());
        orderitem.setQuantity(orderitemreq.getQuantity());
        return orderitem;


    }
}
