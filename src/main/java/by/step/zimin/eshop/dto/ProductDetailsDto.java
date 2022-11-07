package by.step.zimin.eshop.dto;

import by.step.zimin.eshop.model.Color;
import by.step.zimin.eshop.model.OperationSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailsDto {
    private Long id;
    private Color color;
    private Integer countSim;
    private Double displaySize;
    private Date yearProduction;
    private Integer inMemory;
    private Integer ramMemory;
    private Double frontCamera;
    private Double rearCamera;
    private OperationSystem operationSystem;
    private Double versionOS;
    private Integer accumulatorCapacity;
}
