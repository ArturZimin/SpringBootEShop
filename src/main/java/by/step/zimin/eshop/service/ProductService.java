package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    void addProductToUserBucket(Long productId, String username);

    Boolean addProduct(MultipartFile file,ProductDto productDto) throws IOException;

    List<ProductDto> getPhones();

    Integer deleteProduct(Long id);

    

    void minusOneForAmount(Long id);



    Long getAmount(Long id);
}
