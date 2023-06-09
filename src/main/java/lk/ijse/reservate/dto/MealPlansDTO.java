package lk.ijse.reservate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MealPlansDTO {
    private String PackageId;
    private String MealPlan;
    private String MealType;
    private String Description;
    private Double Price;


}
