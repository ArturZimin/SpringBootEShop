package by.step.zimin.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images_app")
public class HeaderImages {


    @Id
    private Long id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String iconCompany;


    public void setId(Long id) {
        this.id = 1L;
    }
    public Long getId(Long id) {
        return 1L;
    }
}
