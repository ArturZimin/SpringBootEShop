package by.step.zimin.eshop.model;

import lombok.*;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories",
            joinColumns=@JoinColumn(name = "product_id"),
            inverseJoinColumns=@JoinColumn(name = "category_id"))
    private List<Category> categories;
}



