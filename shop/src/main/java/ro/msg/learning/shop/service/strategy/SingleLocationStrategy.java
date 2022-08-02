package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.DTO.OrderedProductDTO;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.StockRepository;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
public class SingleLocationStrategy implements LocationStrategy{
    @Autowired
    StockRepository stockRepository;

    @Override
    public List<Stock> findBestLocations(List<OrderedProductDTO> listOfOrderedProducts){
        List<Stock> stocks = stockRepository.findAll();
        Map<Integer, List<Stock>> locationList = new HashMap<>();

        stocks.forEach(stock -> listOfOrderedProducts.forEach(product -> {
            if (stock.getProduct().getId().equals(product.getId()) &&
                stock.getQuantity() >= product.getQuantity()) {
                Integer locationID = stock.getLocation().getId();

                List<Stock> listOfStocksByLocationId = locationList.get(locationID);
                if (listOfStocksByLocationId == null)
                    listOfStocksByLocationId = new ArrayList<>();

                listOfStocksByLocationId.add(stock);
                locationList.put(locationID, listOfStocksByLocationId);
            }
        }));

        List<Stock> singleLocationStocks = null;
        Iterator<Map.Entry<Integer,List<Stock>>> it = locationList.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,List<Stock>> pair = it.next();
            if(pair.getValue().size() == listOfOrderedProducts.size()){
                singleLocationStocks = new ArrayList<>(pair.getValue());
                break;
            }
        }
        return singleLocationStocks;
    }
}
