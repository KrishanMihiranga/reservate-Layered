package lk.ijse.reservate.dto;

import lombok.*;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MealOrderDTO {
    private String MealOrderId;
    private String Qty;
    private String GuestId;
    private String PackageId;
    private Date date;
}
