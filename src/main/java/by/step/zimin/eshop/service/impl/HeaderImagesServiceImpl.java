package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.HeaderImagesDto;
import by.step.zimin.eshop.model.HeaderImages;
import by.step.zimin.eshop.repository.HeaderImagesRepository;
import by.step.zimin.eshop.service.HeaderImagesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@AllArgsConstructor
public class HeaderImagesServiceImpl implements HeaderImagesService {

    @Autowired
    private final HeaderImagesRepository headerImagesRepository;



    @Override
    @Transactional
    public Boolean addImage(MultipartFile file) {
        HeaderImagesDto headerImagesDto=null;
        try {
             headerImagesDto=HeaderImagesDto.builder()
                    .iconCompany(Base64.getEncoder().encodeToString(file.getBytes()))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (headerImagesDto!=null) {
            headerImagesRepository.save(toHeaderImages(headerImagesDto));
            return true;
        }
        return false;
    }

    @Override
    public String getIconCompany() {
    HeaderImages headerImages=  headerImagesRepository.findById(1L).get();
    HeaderImagesDto headerImagesDto=toHeaderImagesDto(headerImages);
        return headerImagesDto.getIconCompany();
    }

    public HeaderImages toHeaderImages(HeaderImagesDto headerImagesDto){
        return  HeaderImages.builder()
                .id(headerImagesDto.getId())
                .iconCompany(headerImagesDto.getIconCompany())
                .build();
    }

    public HeaderImagesDto toHeaderImagesDto(HeaderImages headerImages){
        return  HeaderImagesDto.builder()
               .id(headerImages.getId())
                .iconCompany(headerImages.getIconCompany())
                .build();
    }
}
