package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.strategy.LocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.enums.LocationStrategyEnum;

@Configuration
public class LocationStrategyConfig {
    @Bean
    public LocationStrategy chooseStrategy(@Value("${locationStrategy}")String s) {
        if(s.equalsIgnoreCase(String.valueOf(LocationStrategyEnum.SINGLE_LOCATION_STRATEGY))){
            return new SingleLocationStrategy();
        }
        else if(s.equalsIgnoreCase(String.valueOf(LocationStrategyEnum.MOST_ABUNDANT_STRATEGY))){
            return new MostAbundantStrategy();
        }
        else{
            throw new RuntimeException("Invalid Strategy");
        }
    }
}
