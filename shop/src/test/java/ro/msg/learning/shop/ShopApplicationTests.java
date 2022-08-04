package ro.msg.learning.shop;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ro.msg.learning.shop.integration_test.OrderTest;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.unit_test.MostAbundantStrategyTest;
import ro.msg.learning.shop.unit_test.SingleLocationStrategyTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        OrderTest.class,
        SingleLocationStrategyTest.class,
        MostAbundantStrategyTest.class
})
class ShopApplicationTests {

}
