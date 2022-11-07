package by.step.zimin.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessorDto {
    private Long id;
    private String name;
    private Integer countCore;
    private Integer frequency;
}
