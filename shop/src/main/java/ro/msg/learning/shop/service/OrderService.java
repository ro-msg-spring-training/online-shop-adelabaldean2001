package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.OrderDTO;
import ro.msg.learning.shop.model.OrderedProduct;
import ro.msg.learning.shop.exception.LocationNotFound;
import ro.msg.learning.shop.exception.OrderCannotBeCompleted;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.strategy.LocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final LocationStrategy locationStrategy;
    private final ModelMapper modelMapper;

    public Order mapToEntity(OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO,Order.class);
        order.setProducts(orderDTO.getProducts());
        return order;
    }

    public OrderDTO mapToDTO(Order order){
        OrderDTO orderDTO = modelMapper.map(order,OrderDTO.class);
        return orderDTO;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Integer customerID, Order order) {
        order.setCreatedAt(LocalDateTime.now());

        List<Stock> stocks = findLocationStrategy(order);

        Random rand = new Random();
        Stock stock = stocks.get(rand.nextInt(stocks.size()));

        Customer customer = customerService.getCustomerById(customerID);
        Long orderSizeInitial = orderRepository.count();

        List<OrderedProduct> products = productsList(order);

        order.setShippedFrom(stock.getLocation());
        order.setCustomer(customer);
        orderRepository.save(order);

        Long orderSizeFinal = orderRepository.count();

        if (orderSizeInitial.equals(orderSizeFinal))
            throw new OrderCannotBeCompleted();

        for(OrderedProduct orderedProduct: products) {
            if (orderedProduct.getId().equals(stock.getProduct().getId())) {
                stock.setQuantity(stock.getQuantity() - orderedProduct.getQuantity());
                stockService.createStock(stock);

                OrderDetail orderDetail = new OrderDetail(order, stock.getProduct(), orderedProduct.getQuantity());
                orderDetailService.createOrderDetail(orderDetail);
                break;
            }
        }
        return order;
    }

    private List<OrderedProduct> productsList(Order order) {
        List<OrderedProduct> orderedProducts = new ArrayList<>();

        order.getProducts().forEach((productId, quantity) -> {
            Product product = productService.getProductById(productId);
            OrderedProduct orderedProduct = OrderedProduct.builder()
                    .id(product.getId())
                    .quantity(quantity)
                    .build();
            orderedProducts.add(orderedProduct);
        });
        return orderedProducts;
    }

    private List<Stock> findLocationStrategy(Order order) {
        List<OrderedProduct> products = productsList(order);
        List<Stock> stocks = locationStrategy.findBestLocations(products);

        if (stocks == null || stocks.isEmpty())
            throw new LocationNotFound();

        return stocks;
    }
}