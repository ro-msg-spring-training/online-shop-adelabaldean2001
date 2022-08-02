package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.DTO.OrderedProductDTO;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
public class MostAbundantStrategy implements LocationStrategy{
    @Autowired
    StockRepository stockRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Stock> findBestLocations(List<OrderedProductDTO> listOfOrderedProducts) {
        List<Stock> stocks = stockRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Stock::getQuantity).reversed())
                .collect(Collectors.toList());

        List<Stock> mostAbundantLocationStocks = new ArrayList<>();

        listOfOrderedProducts.forEach(product -> {
            for(Stock stock: stocks) {
                if (stock.getProduct().getId().equals(product.getId()) &&
                        stock.getQuantity() >= product.getQuantity()) {
                    mostAbundantLocationStocks.add(stock);
                    break;
                }
            }
        });

        return mostAbundantLocationStocks;
    }
}
