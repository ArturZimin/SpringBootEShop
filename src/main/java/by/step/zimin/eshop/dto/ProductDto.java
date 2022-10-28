package by.step.zimin.eshop.dto;

import by.step.zimin.eshop.model.Category;
import by.step.zimin.eshop.model.CurrencyType;
import by.step.zimin.eshop.model.Processor;
import by.step.zimin.eshop.model.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private byte[] imageProduct;
    private CurrencyType currencyType;
    private List<Category> categories;
    private ProductDetails productDetails;
    private Processor processor;
}
