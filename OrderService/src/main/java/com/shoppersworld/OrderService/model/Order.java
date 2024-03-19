package com.shoppersworld.OrderService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="ordertable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private  String OrderNumber;
  @OneToMany(cascade = CascadeType.ALL)
  private List<OrderItems> items;

}
