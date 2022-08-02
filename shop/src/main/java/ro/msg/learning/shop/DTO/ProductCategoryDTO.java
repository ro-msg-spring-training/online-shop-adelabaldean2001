package ro.msg.learning.shop.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Integer productCategoryId;
    private String productCategoryName;
    private String productCategoryDescription;
    private Integer supplierId;
    private String supplierName;
    private String imageUrl;
}