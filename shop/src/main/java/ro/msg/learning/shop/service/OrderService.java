package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.OrderDTO;
import ro.msg.learning.shop.DTO.OrderedProductDTO;
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
    private final StockRepository stockRepository;
    private final CustomerRepository customerRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final LocationStrategy locationStrategy;
    private final ModelMapper modelMapper;

    public Order mapToEntity(OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO,Order.class);
        return order;
    }

    public OrderDTO mapToDTO(Order order){
        OrderDTO orderDTO = modelMapper.map(order,OrderDTO.class);
        return orderDTO;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Integer customerID, OrderDTO orderDTO) {
        orderDTO.setCreatedAt(LocalDateTime.now());

        List<Stock> stocks = findLocationStrategy(orderDTO);

        Random rand = new Random();
        Stock stock = stocks.get(rand.nextInt(stocks.size()));

        Customer customer = customerRepository.findById(customerID).get();
        Long orderSizeInitial = orderRepository.count();

        List<OrderedProductDTO> products = productsList(orderDTO);

        Order order = this.mapToEntity(orderDTO);
        order.setShippedFrom(stock.getLocation());
        order.setCustomer(customer);
        orderRepository.save(order);

        Long orderSizeFinal = orderRepository.count();

        if (orderSizeInitial.equals(orderSizeFinal))
            throw new OrderCannotBeCompleted();

        for(OrderedProductDTO orderedProductDTO: products) {
           if (orderedProductDTO.getId().equals(stock.getProduct().getId())) {
               stock.setQuantity(stock.getQuantity() - orderedProductDTO.getQuantity());
               stockRepository.save(stock);

               OrderDetail orderDetail = new OrderDetail(order, stock.getProduct(), orderedProductDTO.getQuantity());
               orderDetailRepository.save(orderDetail);
               break;
           }
        }
        return order;
    }

    private List<OrderedProductDTO> productsList(OrderDTO orderDTO) {
        List<OrderedProductDTO> orderedProducts = new ArrayList<>();

        orderDTO.getProducts().forEach((productId, quantity) -> {
            Product product = productRepository.findById(productId).orElseThrow(() -> new ServiceException("Invalid product id"));
            OrderedProductDTO orderedProductDTO = OrderedProductDTO.builder()
                    .id(product.getId())
                    .quantity(quantity)
                    .build();
            orderedProducts.add(orderedProductDTO);
        });
        return orderedProducts;
    }

    private List<Stock> findLocationStrategy(OrderDTO orderDTO) {
        List<OrderedProductDTO> products = productsList(orderDTO);
        List<Stock> stocks = locationStrategy.findBestLocations(products);

        if (stocks == null || stocks.isEmpty())
            throw new LocationNotFound();

        return stocks;
    }
}
