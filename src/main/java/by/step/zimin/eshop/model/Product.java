package by.step.zimin.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String imageProduct;

    private Long amount;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToOne
    private ProductDetails productDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    private Processor processor;

    public BigDecimal getPrice() {
        if (this.price == null) {
            return new BigDecimal("0.0");
        } else {
            return price;
        }
    }
}



