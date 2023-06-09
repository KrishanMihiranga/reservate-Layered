package lk.ijse.reservate.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class payment {
    private String paymentid;
    private String guestid;
    private String MealOrderId;
    private String hallreservationid;
    private String roomreservationid;
    private String date;
    private String time;
    private String amount;
}