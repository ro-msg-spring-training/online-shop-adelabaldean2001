package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail extends BaseEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderp_id")
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Integer quantity;
}
