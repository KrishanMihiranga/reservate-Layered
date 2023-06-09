package lk.ijse.reservate.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class mealDetailsTM {
    private String PackageId;
    private String MealPlan;
    private String MealType;
    private String Description;
    private Double Price;
}
