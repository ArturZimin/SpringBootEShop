package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.HeaderImagesDto;
import by.step.zimin.eshop.response.Response;
import by.step.zimin.eshop.service.HeaderImagesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/headers")
public class HeaderImagesController {


    private final HeaderImagesService headerImagesService;

    public HeaderImagesController(HeaderImagesService headerImagesService) {
        this.headerImagesService = headerImagesService;
    }


    @GetMapping("/add/iconCompany")
    public String getFormToAddImages(){
        return "addHeaderImage";
    }

    @PostMapping("add/iconCompany")
    public String addHeaderImages( MultipartFile iconCompany, Model model){
        System.out.println(iconCompany);

      Boolean isAdded=  headerImagesService.addImage(iconCompany);
      if (isAdded){
         Response response= new Response(200,"The image was added successfully!",null);
          model.addAttribute("response",response);
          return "addHeaderImage";
      }
        model.addAttribute(new Response(500,"Something went wrong, try again!",null));
        return "addHeaderImage" ;
    }
}
