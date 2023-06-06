package lk.ijse.reservate.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {
    private String ComplaintId;
    private String Date;
    private String time;
    private String guestid;
    private String mealorderid;
    private String hallreservationid;
    private String roomreservationid;
    private String description;
}
