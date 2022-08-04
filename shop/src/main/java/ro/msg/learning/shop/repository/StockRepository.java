package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
    List<Stock> findStocksByProductAndQuantity(Product product, Integer quantity);
}
