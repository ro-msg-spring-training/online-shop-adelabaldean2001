package ro.msg.learning.shop.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedProduct {
    private Integer id;
    private Integer quantity;
}
