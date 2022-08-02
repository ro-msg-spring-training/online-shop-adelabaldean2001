package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory getProductCategoryById(Integer id){
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invalid customer id"));
    }

}
