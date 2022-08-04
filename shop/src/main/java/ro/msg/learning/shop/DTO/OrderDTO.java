package ro.msg.learning.shop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private LocalDateTime createdAt;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreetAddress;
    private Map<Integer, Integer> products;
}
