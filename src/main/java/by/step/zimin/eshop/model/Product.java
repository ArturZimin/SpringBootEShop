package by.step.zimin.eshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String imageProduct;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String imageProduct2;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String imageProduct3;
    private BigDecimal price;
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    private Processor processor;
    @OneToOne
    private ProductDetails productDetails;


    @ManyToOne(fetch = FetchType.EAGER)
    private Discount discount;

    public Product(Long id, String title, BigDecimal price, CurrencyType currencyType, Long amount, Category category, ProductDetails productDetails, Processor processor, Discount discount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.currencyType = currencyType;
        this.amount = amount;
        this.category = category;
        this.productDetails = productDetails;
        this.processor = processor;
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        if (this.price == null) {
            return new BigDecimal("0.0");
        } else {
            return price;
        }
    }

    public String getImageProduct() {
        if (imageProduct == null) {
            return "";
        }
        return imageProduct;
    }

    public String getImageProduct2() {
        if (imageProduct2 == null) {
            return "";
        }
        return imageProduct2;
    }

    public String getImageProduct3() {
        if (imageProduct3 == null) {
            return "";
        }
        return imageProduct3;
    }
}



