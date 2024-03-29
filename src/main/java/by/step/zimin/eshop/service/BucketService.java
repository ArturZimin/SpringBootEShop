package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.BucketDto;
import by.step.zimin.eshop.model.Bucket;
import by.step.zimin.eshop.model.Product;
import by.step.zimin.eshop.model.User;

import java.util.List;

public interface BucketService {
    List<Product> getProductById(List<Long> productsIds);

    Bucket createBucket(User user, List<Long> productIds);

    void addProduct(Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(String name);

    void deleteProduct(Long id, String name);

    Long getAmountInBucket(String name);


}