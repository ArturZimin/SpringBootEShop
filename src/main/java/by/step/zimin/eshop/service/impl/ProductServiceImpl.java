package by.step.zimin.eshop.service.impl;


import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.service.*;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

//    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;
    private final ProductDetailsService productDetailsService;
    private final ProcessorService processorService;
    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService, ProductDetailsService productDetailsService, ProcessorService processorService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
        this.productDetailsService = productDetailsService;
        this.processorService = processorService;
        this.categoryService = categoryService;
    }


    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = productRepository.findAll();
        if (productList == null) {
            throw new RuntimeException("The products not found!");
        }

        log.info("The method getAll() found: " + productList);
        return productListToProductListDto(productList);
    }

    @Override
    @Transactional
    public void addProductToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found : " + username);
        }

        Bucket bucket = user.getBucket();//create bucket from user bucket
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));//только для чтения список(не изменяемый)

            user.setBucket(newBucket);
            userService.save(user);
        } else {

            bucketService.addProduct(bucket, Collections.singletonList(productId));
        }

    }

    @Override
    @Transactional
    public Boolean addProduct(MultipartFile file, MultipartFile file2, MultipartFile file3, ProductDto productDto) throws IOException {


        ProductDetails productDetails = ProductDetails.builder()
                .color(productDto.getColor())
                .frontCamera(productDto.getFrontCamera())
                .rearCamera(productDto.getRearCamera())
                .accumulatorCapacity(productDto.getAccumulatorCapacity())
                .countSim(productDto.getCountSim())
                .displaySize(productDto.getDisplaySize())
                .inMemory(productDto.getInMemory())
                .ramMemory(productDto.getRamMemory())
                .versionOS(productDto.getVersionOS())
                .operationSystem(productDto.getOperationSystem())
                .yearProduction(productDto.getYearProduction())
                .build();

        productDetailsService.addProductDetails(productDetails);

        Processor processor = Processor.builder()
                .name(productDto.getName())
                .countCore(productDto.getCountCore())
                .frequency(productDto.getFrequency())
                .build();
        processorService.addProcessor(processor);

        Category category = Category.builder()
                .categoryTitle(productDto.getCategoryTitle())
                .podCategory(productDto.getPodCategory())
                .build();

        categoryService.addCategory(category);

        Discount discount = new Discount();
        if (productDto.getDiscount() != null) {
            discount.setDiscount(productDto.getDiscount());
        }else if(productDto.getDiscount() == null){
            discount.setDiscount(0);
        }



        Product product = Product.builder()
                .id(productDto.getId())
                .imageProduct(Base64.getEncoder().encodeToString(file.getBytes()))
                .imageProduct2(Base64.getEncoder().encodeToString(file2.getBytes()))
                .imageProduct3(Base64.getEncoder().encodeToString(file3.getBytes()))
                .amount(productDto.getAmount())
                .price(productDto.getPrice())
                .currencyType(productDto.getCurrencyType())
                .productDetails(productDetails)
                .category(category)
                .discount(discount)
                .processor(processor)
                .title(productDto.getTitle())
                .build();


        Product productSaved = productRepository.save(product);

            return true;

    }


    @Override
    public List<ProductDto> getPhones() {
        List<Product> listPhones = productRepository.findAllByCategory_PodCategory("Phone");
        return productListToProductListDto(listPhones);
    }

    @Override
    @Transactional
    public Integer deleteProduct(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return 200;
    }

    @Override
    public Page<ProductDto> findByOrderByTitle(Integer page, Integer size) {
        Page<Product> firstNineProducts = productRepository.findByOrderByTitle(PageRequest.of(page, size));
        return firstNineProducts.map(new Function<Product, ProductDto>() {
            @Override
            public ProductDto apply(Product product) {
                return toDto(product);
            }
        });
    }


    @Override
    @Transactional
    public void minusOneForAmount(Long id) {
        Product product = productRepository.findById(id).get();
        Long amount = product.getAmount();
        product.setAmount(--amount);
        productRepository.save(product);
    }


    @Override
    public Long getAmount(Long id) {
        Product product = productRepository.findById(id).get();
        return product.getAmount();
    }


    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).get();

        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .imageProduct(product.getImageProduct())
                .imageProduct2(product.getImageProduct2())
                .imageProduct3(product.getImageProduct3())
                .amount(product.getAmount())
                .currencyType(product.getCurrencyType())
                .categoryTitle(product.getCategory().getCategoryTitle())
                .podCategory(product.getCategory().getPodCategory())
                .name(product.getProcessor().getName())
                .countCore(product.getProcessor().getCountCore())
                .frequency(product.getProcessor().getFrequency())
                .accumulatorCapacity(product.getProductDetails().getAccumulatorCapacity())
                .versionOS(product.getProductDetails().getVersionOS())
                .operationSystem(product.getProductDetails().getOperationSystem())
                .rearCamera(product.getProductDetails().getRearCamera())
                .frontCamera(product.getProductDetails().getFrontCamera())
                .ramMemory(product.getProductDetails().getRamMemory())
                .inMemory(product.getProductDetails().getInMemory())
                .yearProduction(product.getProductDetails().getYearProduction())
                .displaySize(product.getProductDetails().getDisplaySize())
                .countSim(product.getProductDetails().getCountSim())
                .color(product.getProductDetails().getColor())
                .build();
    }

    @Override
    public List<ProductDto> findProductsByTitleOrCategory(String title, String category) {
        List<Product> productList;
        List<ProductDto> productDtos;
        if (category.equals("All")) {
            productList = productRepository.findAll();
            productDtos = productListToProductListDto(productList.stream()
                    .filter(product -> product.getTitle().contains(title))
                    .collect(Collectors.toList()));
            return productDtos;
        }
        productList = productRepository.findAllByCategory_PodCategory(category);
        productDtos = productListToProductListDto(productList.stream()
                .filter(product -> product.getTitle().contains(title))
                .collect(Collectors.toList()));
        if (productDtos.size() > 0) {
            return productDtos;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllLaptops() {
        List<Product> listProduct = productRepository.findAllByCategory_PodCategory("Notebook");

        return productListToProductListDto(listProduct);
    }


    @Override
    public List<ProductDto> getAllWatches() {
        List<Product> listProduct = productRepository.findAllByCategory_PodCategory("Watch");

        return productListToProductListDto(listProduct);
    }

    @Override
    public List<ProductDto> getAllAccessories() {
        List<Product> listProduct = productRepository.findAllByCategory_PodCategory("Accessory");

        return productListToProductListDto(listProduct);
    }

    @Override
    public List<ProductDto> getAllTablets() {
        List<Product> listProduct = productRepository.findAllByCategory_PodCategory("Tablet");

        return productListToProductListDto(listProduct);
    }

    @Override
    public List<ProductDto> getAllCameras() {
        List<Product> listProduct = productRepository.findAllByCategory_PodCategory("Camera");

        return productListToProductListDto(listProduct);
    }

    @Override
    public List<ProductDto> getAllProductsSortByPrice() {
        List<Product> productDtoList = productRepository.findAllByOrderByPrice();
        return productListToProductListDto(productDtoList);
    }

    @Override
    public List<ProductDto> getAllProductsSortByYear() {
        List<Product> productDtoList = productRepository.findAllByOrderByProductDetails_YearProductionDesc();
        return productListToProductListDto(productDtoList);
    }


    public List<ProductDto> productListToProductListDto(List<Product> product) {
        return product.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public List<Product> productListDTOToProductList(List<ProductDto> product) {
        return product.stream()
                .map(this::toProduct)
                .collect(Collectors.toList());

    }

    @Override
    public Product toProduct(ProductDto productDto) {
       Discount discountDto = new Discount();
       discountDto.setDiscount(productDto.getDiscount());
        return Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .imageProduct(productDto.getImageProduct())
                .currencyType(productDto.getCurrencyType())
                .productDetails(productDto.getProductDetails())
                .processor(productDto.getProcessor())
                .amount(productDto.getAmount())
                .discount(discountDto)
                .build();

    }

    @Override
    public ProductDto toDto(Product product) {
        Discount discountProduct=new Discount();
        if (product.getDiscount()==null){
            discountProduct.setDiscount(0);
        }else  if (product.getDiscount().getDiscount()!=null){
            discountProduct.setDiscount(product.getDiscount().getDiscount());
        }

        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .imageProduct(product.getImageProduct())
                .imageProduct2(product.getImageProduct2())
                .imageProduct3(product.getImageProduct3())
                .category(product.getCategory())
                .currencyType(product.getCurrencyType())
                .productDetails(product.getProductDetails())
                .processor(product.getProcessor())
                .amount(product.getAmount())
                .discount(discountProduct.getDiscount())
                .build();
    }
}
