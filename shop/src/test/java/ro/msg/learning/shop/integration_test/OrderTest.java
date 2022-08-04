package ro.msg.learning.shop.integration_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.shop.DTO.OrderDTO;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class OrderTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private OrderService orderService;

    @Before
    public void initSuccessfulOrderTest() throws Exception {
        String uri = "/api/order";
        mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    @Test
    public void successCreatingOrder() {
        Map<Integer, Integer> products  = new HashMap<Integer, Integer>() {{
            put(1,2);
            put(2,2);
            put(4,5);
        }};

        Customer customer = Customer.builder()
                .firstName("Evie")
                .lastName("Kalfr")
                .username("adantiou")
                .password("$2a$10$/8sJI32uGKmzcrz4NT9uH.XYtuNrr3bqAgnUDW9Bf8RIxBSS2ZRgC")
                .emailAddress("eimear@gmail.com")
                .build();
        customer.setId(1);

        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(),"Romania","Cluj-Napoca","Cluj","Oasului", products);
        Order order = orderService.createOrder(customer.getId(), orderService.mapToEntity(orderDTO));

        assert (order != null);
    }

    @Test(expected = RuntimeException.class)
    public void failedOrderCreation() {
        Map<Integer, Integer> products  = new HashMap<Integer, Integer>() {{
            put(1,222222);
            put(2,222222);
            put(4,222222);
        }};

        Customer customer = Customer.builder()
                .firstName("Evie")
                .lastName("Kalfr")
                .username("adantiou")
                .password("$2a$10$/8sJI32uGKmzcrz4NT9uH.XYtuNrr3bqAgnUDW9Bf8RIxBSS2ZRgC")
                .emailAddress("eimear@gmail.com")
                .build();
        customer.setId(1);

        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(),"Romania","Cluj-Napoca","Cluj","Odobesti 2", products);
        orderService.createOrder(customer.getId(),orderService.mapToEntity(orderDTO));
    }

    @After
    public void clear() throws Exception {
        String uri = "/api/clear";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        mvcResult.getResponse().getStatus();
    }
}
