package lk.ijse.reservate.entity;

import lombok.*;

import java.sql.Date;

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
    private Date date;


}
