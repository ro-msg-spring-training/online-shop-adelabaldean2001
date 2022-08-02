package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.OrderDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{customerID}")
    private Order createOrder(@PathVariable Integer customerID, @RequestBody OrderDTO orderDTO) throws Exception {
        return orderService.createOrder(customerID, orderDTO);
    }

    @GetMapping("/get")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders()
                .stream()
                .map(this.orderService::mapToDTO)
                .collect(Collectors.toList());
    }
}
