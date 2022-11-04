package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {


    private final ProductService productService;



    @GetMapping
    public String list(Model model) {
        List<ProductDto> list = productService.getAll();//достали все продукты из bd и положили в list
        model.addAttribute("products", list);//sent list to products.html
        return "products";
    }

    /**
     * method add bucket to  user
     */
    @GetMapping("/{id}/bucket")
    public String addProductToBucket(@PathVariable Long id, Principal principal ){
        if (principal==null){
            return "redirect:/products";
        }
        productService.addProductToUserBucket(id,principal.getName());
        return "redirect:/products";
    }

    @GetMapping("/add/form")
    public String getFormAddProduct(){
        return "addProduct";
    }



    @PostMapping("/add")
    public String addProductByAllProducts(@RequestParam("image") MultipartFile file, ProductDto productDto, Model model, Principal principal) {
        if (productDto==null){
            throw new RuntimeException("The product is null!");
        }

        System.out.println(productDto);
        Boolean isAdd= null;
        try {
            isAdd = productService.addProduct(file,productDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("isAdd",isAdd);
            return "addProduct";

    }
}
/**
 * @PostMapping("/profile-picture")
 *     public ResponseEntity uploadImage(@RequestParam("file") MultipartFile imageFile, @ModelAttribute UserDTO requestDto) {
 *      try {
 *           UserDTO created  =userDetailsService.saveImg(requestDto, file.getBytes());
 *           return new ResponseEntity<>(created, HttpStatus.OK);
 *
 *     } catch (IOException e) {
 *         // TODO Auto-generated catch block
 *         e.printStackTrace();
 *     }
 *     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
 * }
 */