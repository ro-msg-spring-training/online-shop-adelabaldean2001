package ro.msg.learning.shop.Model;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Supplier extends BaseEntity{
    private String name;
}
