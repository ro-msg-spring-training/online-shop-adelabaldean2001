package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.DTO.OrderedProductDTO;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface LocationStrategy {
    List<Stock> findBestLocations(List<OrderedProductDTO> listOfOrderedProducts);
}
