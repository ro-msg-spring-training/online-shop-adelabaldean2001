package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public Supplier getSupplierById(Integer id){
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invalid customer id"));
    }

}
