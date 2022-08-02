package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductCategoryDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public ProductCategoryDTO mapToDTO(Product product){
        ProductCategoryDTO productCategoryDTO = modelMapper.map(product,ProductCategoryDTO.class);
        return productCategoryDTO;
    }

    public Product mapToEntity(ProductCategoryDTO productCategoryDTO){
        Product product = modelMapper.map(productCategoryDTO,Product.class);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invalid product id"));
    }

    public void createProduct(ProductCategoryDTO productCategoryDTO){
        productCategoryRepository.findById(productCategoryDTO.getProductCategoryId())
                .orElseThrow(() -> new ServiceException("Invalid product category id"));

        supplierRepository.findById(productCategoryDTO.getSupplierId())
                .orElseThrow(() -> new ServiceException("Invalid supplier id"));

        Product product = this.mapToEntity(productCategoryDTO);
        productRepository.save(product);
    }

    public void updateProduct(Integer id, ProductCategoryDTO productCategoryDTO){
        productCategoryRepository.findById(productCategoryDTO.getProductCategoryId())
                .orElseThrow(() -> new ServiceException("Invalid product category id"));


        supplierRepository.findById(productCategoryDTO.getSupplierId())
                .orElseThrow(() -> new ServiceException("Invalid supplier id"));

        productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invalid product id"));

        Product product = this.mapToEntity(productCategoryDTO);
        product.setId(id);
        productRepository.save(product);
    }

    public void deleteProduct(Integer id, ProductCategoryDTO productCategoryDTO){
        productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invalid product id"));

        productRepository.deleteById(id);
    }
}
