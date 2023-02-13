package by.step.zimin.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDto {

    private Integer amountProducts;
    private Double sum;
    private List<BucketDetailsDto> details = new ArrayList<>();



    public void aggregate(){

        this.amountProducts=details.size();//Returns:the number of elements in this list
        this.sum=details.stream()
                .map(BucketDetailsDto::getSum)//достаем сумму
                .mapToDouble(Double::doubleValue)//Params: mapper – a non-interfering, stateless function to apply to each element Returns: the new stream
                .sum();//return sum of elements this stream

    }
}
