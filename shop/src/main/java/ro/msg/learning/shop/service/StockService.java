package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public void createStock(Stock stock){
        stockRepository.save(stock);
    }
}
