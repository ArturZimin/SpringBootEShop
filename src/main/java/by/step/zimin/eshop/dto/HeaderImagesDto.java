package by.step.zimin.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeaderImagesDto {
    private Long id ;
    private String iconCompany;

    public Long getId() {
        return 1L;
    }

    public void setId(Long id) {
        this.id = 1L;
    }
}
