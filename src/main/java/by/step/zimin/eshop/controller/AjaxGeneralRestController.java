package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.response.Response;
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
public class AjaxGeneralRestController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final BucketService bucketService;


    //метод для сохранения
    @PostMapping("/product/add/to/bucket/{id}")//что-то добавляем
    public Response addToBucketWithAjax(@PathVariable Long id, Principal principal) { //request in body
        if (principal == null) {
            ResponseEntity.badRequest().body(id);
            return new Response(401,"You need to log in!",null); //ResponseEntity.badRequest().body("You need to log in!");
        }
        Long amount = productService.getAmount(id);
        if (amount > 0) {
            productService.minusOneForAmount(id);
            productService.addProductToUserBucket(id, principal.getName());
            Long amountInBucket = bucketService.getAmountInBucket(principal.getName());
            return new Response(200,"The product was added to bucket successfully!",amountInBucket); //ResponseEntity.ok("The product was added to bucket successfully!");
        } else {
            return new Response(404,"The product amount is 'ZERO' and can't be add to bucket!",null);//ResponseEntity.badRequest().body("The product amount is 'ZERO' and can't be add to bucket!");
        }
    }
}