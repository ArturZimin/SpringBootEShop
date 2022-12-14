package by.step.zimin.eshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "processors")
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   private String name;
   private Integer countCore;
   private Integer frequency;

}
