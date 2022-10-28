package by.step.zimin.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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
