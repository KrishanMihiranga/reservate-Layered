package lk.ijse.reservate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class selectMealDTO {
    private String MealOrderId;
    private String Qty;
    private String GuestId;
    private String PackageId;
    private String Date;
}
