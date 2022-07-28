package ro.msg.learning.shop.Model;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCategory extends BaseEntity{
    private String name;
    private String description;
}
