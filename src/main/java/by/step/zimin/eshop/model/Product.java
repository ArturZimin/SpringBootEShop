package by.step.zimin.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    private byte[] imageProduct;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @OneToOne
    private ProductDetails productDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    private Processor processor;
}



