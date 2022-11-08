package by.step.zimin.eshop.dto;

import by.step.zimin.eshop.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String imageProduct;
    private String imageProduct2;
    private String imageProduct3;
    private CurrencyType currencyType;
    private Category category;
    private ProductDetails productDetails;
    private Processor processor;
    private Long amount;

//    product details
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


//    processor
    private String name;
    private Integer countCore;
    private Integer frequency;

    //category
    private String categoryTitle;
    private String podCategory;
}
