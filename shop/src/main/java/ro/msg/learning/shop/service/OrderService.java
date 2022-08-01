package ro.msg.learning.shop.service;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.OrderDTO;
import ro.msg.learning.shop.DTO.OrderedProductDTO;
import ro.msg.learning.shop.exception.LocationNotFound;
import ro.msg.learning.shop.exception.OrderCannotBeCompleted;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.strategy.LocationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private LocationStrategy locationStrategy;

    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public Order mapToEntity(OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO,Order.class);
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

    private List<Stock> findLocationStrategy(OrderDTO orderDTO) throws Exception {
        List<OrderedProductDTO> products = productsList(orderDTO);
        List<Stock> stocks = locationStrategy.findBestLocations(products);

        if (stocks == null || stocks.isEmpty())
            throw new LocationNotFound();

        return stocks;
    }

    public Order createOrder(Integer customerID, OrderDTO orderDTO) throws Exception {
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

        for(OrderedProductDTO orderedProductDTO : products) {
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

}
