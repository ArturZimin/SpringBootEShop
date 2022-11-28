package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.HeaderImagesDto;
import org.springframework.web.multipart.MultipartFile;

public interface HeaderImagesService {
    Boolean addImage( MultipartFile file);

    String getIconCompany();
}
