package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.model.OrderedProduct;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
public class MostAbundantStrategy implements LocationStrategy{
    @Autowired
    StockRepository stockRepository;

    @Override
    public List<Stock> findBestLocations(List<OrderedProduct> listOfOrderedProducts) {
        List<Stock> stocks = stockRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Stock::getQuantity).reversed())
                .collect(Collectors.toList());

        List<Stock> mostAbundantStocks = new ArrayList<>();

        listOfOrderedProducts.forEach(product -> {
            for(Stock stock: stocks) {
                if (stock.getProduct().getId().equals(product.getId()) &&
                        stock.getQuantity() >= product.getQuantity()) {
                    mostAbundantStocks.add(stock);
                    break;
                }
            }
        });
        return mostAbundantStocks;
    }
}
