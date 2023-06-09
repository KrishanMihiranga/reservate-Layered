package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class hallReservation {
    private String CheckIn;
    private String CheckOut;
    private String HallReservationId;
    private String GuestId;
    private String HallNumber;

}
