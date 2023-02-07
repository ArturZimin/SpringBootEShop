package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void save(Product product);

    Product toProduct(ProductDto productDto);

    ProductDto toDto(Product product);

    List<ProductDto> getAll();

    void addProductToUserBucket(Long productId, String username);

    Boolean addProduct(MultipartFile file, MultipartFile file2, MultipartFile file3, ProductDto productDto) throws IOException;

    List<ProductDto> getPhones();

    Integer deleteProduct(Long id);

    Page<ProductDto> findByOrderByTitle(Integer page, Integer size);

    void minusOneForAmount(Long id);

    List<Product> productListDTOToProductList(List<ProductDto> product);

    List<ProductDto> productListToProductListDto(List<Product> product);

    Long getAmount(Long id);

//    Integer changeProductById(Long id);

    ProductDto getProductById(Long productId);


    List<ProductDto> findProductsByTitleOrCategory(String title, String category);

    List<ProductDto> getAllLaptops();

    List<ProductDto> getAllWatches();

    List<ProductDto> getAllAccessories();

    List<ProductDto> getAllTablets();

    List<ProductDto> getAllCameras();

    List<ProductDto> getAllProductsSortByPrice();

    List<ProductDto> getAllProductsSortByYear();
}
