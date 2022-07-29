package ro.msg.learning.shop;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ro.msg.learning.shop.model.Customer;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ShopApplicationTests {

    @Test
    void contextLoads() {
        Customer testEntity = new Customer();
        Set<Customer> set = new HashSet<>();

        set.add(testEntity);
        //testEntityRepository.save(testEntity);

        Assert.isTrue(set.contains(testEntity), "Entity not found in the set");
    }

}
