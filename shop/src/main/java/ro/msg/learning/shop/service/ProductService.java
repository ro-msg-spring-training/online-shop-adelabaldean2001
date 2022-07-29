package ro.msg.learning.shop.service;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductCategoryDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    SupplierRepository supplierRepository;


    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

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

    public Optional<Product> getProductById(Integer id){
        return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invalid product id")));
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
