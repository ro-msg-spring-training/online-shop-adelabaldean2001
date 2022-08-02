package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.strategy.LocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantLocationStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

@Configuration
public class LocationStrategyConfig {

    @Bean
    public LocationStrategy chooseStrategy(@Value("${strategy}")String s) {
        if(s.equals("SingleLocationStrategy")){
            return new SingleLocationStrategy();
        }
        else if(s.equals("MostAbundantLocationStrategy")){
            return new MostAbundantLocationStrategy();
        }
        else{
            throw new RuntimeException("Invalid Strategy");
        }
    }
}
