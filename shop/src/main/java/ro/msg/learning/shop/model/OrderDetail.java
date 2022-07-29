package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail extends BaseEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Integer quantity;
}
