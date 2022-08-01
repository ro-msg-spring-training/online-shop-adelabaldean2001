package ro.msg.learning.shop.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedProductDTO {
    private Integer id;
    private Integer quantity;
}
