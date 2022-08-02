package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategory extends BaseEntity{
    private String name;
    private String description;
}
