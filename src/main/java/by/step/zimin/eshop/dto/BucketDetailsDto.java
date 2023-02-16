package by.step.zimin.eshop.dto;

import by.step.zimin.eshop.model.Discount;
import by.step.zimin.eshop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailsDto {
    private String imageProduct;
    private String title;
    private Long productId;
    private BigDecimal price;
    private BigDecimal amount;
    private Double sum;
    private Discount discount;

    public BucketDetailsDto(Product product) {
        this.discount=product.getDiscount();
        this.imageProduct=product.getImageProduct();
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.amount=new BigDecimal("1.0");
      double summ = Double.parseDouble(product.getPrice().toString());
      if (product.getDiscount().getDiscount()!=null &&product.getDiscount().getDiscount()>0){
          this.sum=(summ/100)*(100-product.getDiscount().getDiscount());
      }else {
          this.sum=summ;
      }
    }
}
