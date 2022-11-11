package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/ajax")
@AllArgsConstructor
public class MyRESTController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final BucketService bucketService;


    //метод для сохранения
    @PostMapping("/product/add/to/bucket/{id}")//что-то добавляем
    public ResponseEntity addToBucketWithAjax(@PathVariable Long id, Principal principal) { //request in body
        System.out.println(id);
        if (principal == null) {
            ResponseEntity.badRequest().body(id);
            return ResponseEntity.badRequest().body("You need to log in!");
        }
        Long amount = productService.getAmount(id);
        if (amount > 0) {
            productService.minusOneForAmount(id);
            productService.addProductToUserBucket(id, principal.getName());
            Long amountInBucket = bucketService.getAmountInBucket(principal.getName());
            return ResponseEntity.ok("The product was added successfully!");
        } else {
            return ResponseEntity.badRequest().body("The product amount is 'ZERO' and won't be able added to bucket!");
        }
    }
}