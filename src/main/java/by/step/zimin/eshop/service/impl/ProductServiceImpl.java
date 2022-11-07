package by.step.zimin.eshop.service.impl;


import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

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
    public Boolean addProduct(MultipartFile file,ProductDto productDto) throws IOException {


        ProductDetails productDetails=ProductDetails.builder()
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

        System.out.println(productDetails);
        productDetailsService.addProductDetails(productDetails);

        Processor processor= Processor.builder()
                .name(productDto.getName())
                .countCore(productDto.getCountCore())
                .frequency(productDto.getFrequency())
                .build();

        System.out.println(processor);
        processorService.addProcessor(processor);

        Category category=Category.builder()
                .categoryTitle(productDto.getCategoryTitle())
                .podCategory(productDto.getPodCategory())
                .build();

        System.out.println(category);
        categoryService.addCategory(category);

        Product product =Product.builder()
                .imageProduct(Base64.getEncoder().encodeToString(file.getBytes()))
                .amount(productDto.getAmount())
                .price(productDto.getPrice())
                .currencyType(productDto.getCurrencyType())
                .productDetails(productDetails)
                .category(category)
                .processor(processor)
                .title(productDto.getTitle())
                .build();



        Product productSaved = productRepository.save(product);
        if (productSaved.getId() != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<ProductDto> getPhones() {
        List<Product> listPhones=productRepository.findAllByCategory_PodCategory("phone");
        List<ProductDto> listPhonesDto=productListToProductListDto(listPhones);

        return listPhonesDto;
    }

    @Override
    public Integer deleteProduct(Long id) {
        Product product=productRepository.findById(id).get();
        if (product==null){

            return 404;

        }else {
            productRepository.delete(product);
            return 200;
        }
    }



    @Override
    public void minusOneForAmount(Long id) {
        Product product=productRepository.findById(id).get();
        Long amount=product.getAmount();
        product.setAmount(--amount);
        productRepository.save(product);
    }



    @Override
    public Long getAmount(Long id) {
        Product product=productRepository.findById(id).get();
        return product.getAmount();
    }

    public List<ProductDto> productListToProductListDto(List<Product> product) {
        List<ProductDto> list = product.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Product toProduct(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .imageProduct(productDto.getImageProduct())
                .currencyType(productDto.getCurrencyType())
                .productDetails(productDto.getProductDetails())
                .processor(productDto.getProcessor())
                .amount(productDto.getAmount())
                .build();
        return product;
    }

    public ProductDto toDto(Product product) {
        ProductDto dto = ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .imageProduct(product.getImageProduct())
                .category(product.getCategory())
                .currencyType(product.getCurrencyType())
                .productDetails(product.getProductDetails())
                .processor(product.getProcessor())
                .amount(product.getAmount())
                .build();
        return dto;
    }
}
