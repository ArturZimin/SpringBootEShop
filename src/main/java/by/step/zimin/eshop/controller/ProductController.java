package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


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

    @GetMapping("/add/products/form")
    public String getFormAddProduct(){
        return "addProductsForm";
    }

    @PostMapping("/add/products")
    public String addProductByAllProducts(@RequestBody ProductDto productDto,Model model){
        if (productDto==null){
            throw new RuntimeException("The product is null!");

        }else {
            Boolean isAdd=productService.addProduct(productDto);
            model.addAttribute("isAdd",isAdd);
            return "addProductsForm";
        }
    }
}
