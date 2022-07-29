package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product extends BaseEntity{
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategory category;
    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;
    private String imageUrl;
}
