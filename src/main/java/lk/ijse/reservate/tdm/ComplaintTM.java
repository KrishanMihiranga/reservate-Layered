package lk.ijse.reservate.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ComplaintTM {
    private String ComplaintID;
    private String Date;
    private String time;
    private String guestid;
    private String mealorderid;
    private String hallreservationid;
    private String roomreservationid;
    private String description;
}
