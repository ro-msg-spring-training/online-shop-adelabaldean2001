package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.OrderDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders/{customerID}")
    private Order createOrder(@PathVariable Integer customerID, @RequestBody OrderDTO newOrder) throws Exception {
        newOrder.setCreatedAt(LocalDateTime.now().withNano(0));
        return orderService.createOrder(customerID, newOrder);
    }
    
}
