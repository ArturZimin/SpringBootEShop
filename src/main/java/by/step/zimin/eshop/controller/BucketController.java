package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.BucketDto;
import by.step.zimin.eshop.service.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/buckets")
public class BucketController {

    private BucketDto bucketDto;
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }


    @GetMapping("/get")
    public String getBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDto());
        } else {
            bucketDto = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDto);
        }
        return "bucket";
    }

    @GetMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable Long id, Model model, Principal principal) {
        bucketService.deleteProduct(id, principal.getName());
        bucketDto = bucketService.getBucketByUser(principal.getName());
        model.addAttribute("bucket", bucketDto);
        return "bucket";
    }
}
