package ro.msg.learning.shop.unit_test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class SingleLocationStrategyTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;

    @Before
    public void initSingleLocationStrategyTest(){
        MockitoJUnit.rule();

        ProductCategory productCategory = new ProductCategory("electronics", "technology");
        productCategory.setId(1);

        Supplier supplier = new Supplier("Electronic Shop");
        supplier.setId(1);

        Product product1 = new Product("samsung", "black", new BigDecimal(256), 2.6, productCategory, supplier,"phoneUrl");
        product1.setId(1);

        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));

        Location location1 = new Location("l1","Romania","Cluj-Napoca","Cluj","Oasului");
        location1.setId(1);

        Stock stock = new Stock(product1,location1,22);
        stock.setId(2);

        List<Stock> mockStock = new ArrayList<>();
        mockStock.add(stock);

        when(stockRepository.findAll()).thenReturn(mockStock);
    }

    @Test
    public void successRunningStrategy(){
        List<OrderedProduct> listOfStocksByLocationId = new ArrayList<>();
        listOfStocksByLocationId.add(new OrderedProduct(1, 9));

        List<Stock> stocks = singleLocationStrategy.findBestLocations(listOfStocksByLocationId);

        assert (!stocks.isEmpty());
    }

    @Test
    public void failRunningStrategy() {
        List<OrderedProduct> lst = new ArrayList<>();
        lst.add(new OrderedProduct(1, 10));
        lst.add(new OrderedProduct(2, 100));

        singleLocationStrategy.findBestLocations(lst);
    }
}
