package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductCategoryDTO;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public List<ProductCategoryDTO> getAllProducts(){
        return productService.getAllProducts()
                .stream()
                .map(this.productService::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ProductCategoryDTO getProductById(@PathVariable(value = "id") Integer id){
        return this.productService.mapToDTO(productService.getProductById(id));
    }

    @PostMapping("/create")
    public void createProduct(@RequestBody ProductCategoryDTO productCategoryDTO) {
        productService.createProduct(productCategoryDTO);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable(value = "id") Integer id, @RequestBody ProductCategoryDTO productCategoryDTO){
        productService.updateProduct(id,productCategoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable(value = "id") Integer id, @RequestBody ProductCategoryDTO productCategoryDTO){
        productService.deleteProduct(id,productCategoryDTO);
    }
}
