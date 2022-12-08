package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.BucketDetailsDto;
import by.step.zimin.eshop.dto.BucketDto;
import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.Bucket;
import by.step.zimin.eshop.model.Product;
import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.repository.BucketRepository;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getProductById(productIds);
        bucket.setProductList(productList);

        return bucketRepository.save(bucket);
    }

    public List<Product> getProductById(List<Long> productsIds) {
        return productsIds.stream()
                .map(productRepository::getReferenceById)//get reference on the Product, by id
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addProduct(Bucket bucket, List<Long> productIds) {//list id of product
        List<Product> products = bucket.getProductList();//add to list all product from bucket
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getProductById(productIds));
        bucket.setProductList(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDto getBucketByUser(String name) {
        User user = userService.findByName(name);//находим узера
        if (user == null || user.getBucket() == null) {//проверяем на налл
            return new BucketDto();//возвращаем пустой BucketDto
        }

        BucketDto bucketDto = new BucketDto();//иначе создаем новый дто
        Map<Long, BucketDetailsDto> mapByProductId = new HashMap<>();//создаем мапу с ключом ид и значением BucketDetailsDto

        List<Product> productList = user.getBucket().getProductList();//находим продуктЛист у юзера
        for (Product product : productList) {//перебираем его
            BucketDetailsDto detailsDto = mapByProductId.get(product.getId());
            //проверяем ли пустая мапа
            if (detailsDto == null) {
                mapByProductId.put(product.getId(), new BucketDetailsDto(product));//добавляем в мапу
            } else {
                detailsDto.setImageProduct(product.getImageProduct());
                detailsDto.setAmount(detailsDto.getAmount().add(new BigDecimal(1.0)));//добавляем к количеству + 1
                detailsDto.setSum(detailsDto.getSum() + Double.valueOf(product.getPrice().toString()));//плюсуем  общую сумму
            }
        }
        bucketDto.setDetails(new ArrayList<>(mapByProductId.values()));// создаем новый список ,добавляем значения из мапы ,сетим детали корзины в корзину дто
        bucketDto.aggregate();//собираем корзину для представления пользователю
        return bucketDto;
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId, String name) {
        User user = userService.findByName(name);//находим узера
        if (user == null || user.getBucket() == null) {//проверяем на налл
            throw new RuntimeException("User not found or bucket is null!");
        }
        userService.deleteProductFromBucketById(productId, user.getId());

    }

    @Override
    public Long getAmountInBucket(String name) {
        User user = userService.findByName(name);//находим узера
        if (user == null || user.getBucket() == null) {//проверяем на налл
            return 0L;
        }else{

            Integer amount= user.getBucket().getProductList().size();
            Long result=amount.longValue();
            return result;
        }

    }


}
