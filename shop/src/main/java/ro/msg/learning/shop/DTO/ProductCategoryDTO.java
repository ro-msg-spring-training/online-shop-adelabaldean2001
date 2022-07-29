package ro.msg.learning.shop.DTO;

import lombok.*;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

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

    public static Product convertDtoToModel(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = ProductCategory.builder()
                .name(productCategoryDTO.getProductCategoryName())
                .description(productCategoryDTO.getProductCategoryDescription())
                .build();
        productCategory.setId(productCategoryDTO.getProductCategoryId());

        Supplier supplier = Supplier.builder()
                .name(productCategoryDTO.getSupplierName())
                .build();
        supplier.setId(productCategoryDTO.getSupplierId());

        Product product = Product.builder()
                .name(productCategoryDTO.getName())
                .description(productCategoryDTO.getDescription())
                .price(productCategoryDTO.getPrice())
                .weight(productCategoryDTO.getWeight())
                .category(productCategory)
                .supplier(supplier)
                .imageUrl(productCategoryDTO.getImageUrl())
                .build();
        product.setId(productCategoryDTO.getId());
        return product;
    }

}