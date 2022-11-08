package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.BucketDto;
import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.ProductService;
import lombok.AllArgsConstructor;
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
    private final BucketService bucketService;


    @GetMapping("/get/phones")
    public String getAllPhones(Model model, Principal principal) {
        List<ProductDto> listPhones = productService.getPhones();
        if (listPhones == null) {
            throw new RuntimeException("The phones not found!");
        }
        if (principal!=null){
            Long amount=  bucketService.getAmountInBucket(principal.getName());
            model.addAttribute("amount",amount);
        }
        model.addAttribute("phones", listPhones);
        return "phones";
    }

    @GetMapping
    public String getListProducts(Model model) {
        List<ProductDto> list = productService.getAll();//достали все продукты из bd и положили в list
        model.addAttribute("products", list);//sent list to products.html
        return "products";
    }

    /**
     * method add bucket to  user
     */
    @GetMapping("/{id}/{page}/bucket")
    public String addProductToBucket(@PathVariable Long id, @PathVariable String page, Principal principal) {
        if (principal == null) {
            return "redirect:/login" ;
        }
        Long amount=productService.getAmount(id);
        if (amount > 0) {
            productService.minusOneForAmount(id);
            productService.addProductToUserBucket(id, principal.getName());

        } else {
            return "redirect:/" + page;
        }
        if (page.equals("phones")) {
            return "redirect:/products/get/" + page;//перенаправление ->/get/phones
        } else {
            return "redirect:/" + page;
        }
    }

    @GetMapping("/add/form")
    public String getFormAddProduct() {
        return "addProduct";
    }


    @PostMapping("/add")
    public String addProductByAllProducts(@RequestParam("image") MultipartFile image,@RequestParam("image2") MultipartFile image2,@RequestParam("image3") MultipartFile image3, ProductDto productDto, Model model, Principal principal) throws IOException {
        if (productDto == null) {
            throw new RuntimeException("The product is null!");
        }

        System.out.println(productDto);
        Boolean isAdd = null;
        isAdd = productService.addProduct(image,image2,image3, productDto);
        model.addAttribute("isAdd", isAdd);
        return "addProduct";

    }

    @GetMapping("/delete/{id}/{page}")
    public String deleteProduct(@PathVariable Long id, @PathVariable String page, Model model, Principal principal) {

        Integer response = productService.deleteProduct(id);
        model.addAttribute("response", response);
        if (page.equals("phones")) {
            return "redirect:/products/get/" + page;
        } else {
            return "index";
        }

    }

    @GetMapping("/get/form/change/{productId}/{page}")
    public String getFormForChangeProduct(@PathVariable Long productId,@PathVariable String page, Model model,Principal principal) {
        ProductDto productDto=productService.getProductById(productId);
        model.addAttribute("productDto",productDto);
        model.addAttribute("page",page);
        return "updateProduct";
    }

//    @PostMapping("/change")
//    public String changeProduct(@PathVariable Long id,@PathVariable String page,Model model,Principal principal){
//        Integer response=productService.changeProductById(id);
//        model.addAttribute("response",response);
//        if (page.equals("phones")){
//            return "redirect:/products/get/" + page;
//        }else {
//            return "index";
//        }
//    }
}
/**
 * @PostMapping("/profile-picture") public ResponseEntity uploadImage(@RequestParam("file") MultipartFile imageFile, @ModelAttribute UserDTO requestDto) {
 * try {
 * UserDTO created  =userDetailsService.saveImg(requestDto, file.getBytes());
 * return new ResponseEntity<>(created, HttpStatus.OK);
 * <p>
 * } catch (IOException e) {
 * // TODO Auto-generated catch block
 * e.printStackTrace();
 * }
 * return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
 * }
 */