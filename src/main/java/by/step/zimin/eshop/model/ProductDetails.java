package by.step.zimin.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.Year;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "count_sim")
    private Integer countSim;
    @Column(name = "display_size")
    private Double displaySize;

    @Column(name = "year_production")
    private Date yearProduction;
    @Column(name = "in_memory")
    private Integer inMemory;
    @Column(name = "ram_memory")
    private Integer ramMemory;
    @Column(name = "front_camera")
    private Double frontCamera;
    @Column(name = "rear_camera")
    private Double rearCamera;
    @Enumerated(EnumType.STRING)
    private OperationSystem operationSystem;
    private Double versionOS;
    private Integer accumulatorCapacity;
}
