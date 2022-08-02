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
public class Supplier extends BaseEntity{
    private String name;
}
