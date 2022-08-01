package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.strategy.LocationStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

@Configuration
public class ClassConfig {

    @Bean
    public LocationStrategy chooseStrategy(@Value("${strategy}")String s) throws Exception {
        if(s.equals("SingleLocationStrategy")){
            return new SingleLocationStrategy();
        }
//        else if(s.equals("MostAbundantStrategy")){
//            return new MostAbundantStrategy();
//        }
        else{
            throw new RuntimeException("Invalid Strategy");
        }
    }
}
