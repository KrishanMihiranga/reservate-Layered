package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class mealDetails {
    private String PackageId;
    private String MealPlan;
    private String MealType;
    private String Description;
    private Double Price;
}
