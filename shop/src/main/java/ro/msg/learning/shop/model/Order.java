package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    private Location shippedFrom;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    private LocalDateTime createdAt;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreetAddress;
}
