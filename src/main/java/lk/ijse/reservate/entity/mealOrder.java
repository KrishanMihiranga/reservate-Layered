package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class mealOrder {
    private String MealOrderId;
    private String Qty;
    private String GuestId;
    private String PackageId;
    private String Date;

}
