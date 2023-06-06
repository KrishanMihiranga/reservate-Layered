package lk.ijse.reservate.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDTO {
    private String paymentid;
    private String guestid;
    private String MealOrderId;
    private String hallreservationid;
    private String roomreservationid;
    private String date;
    private String time;
    private String amount;
}
