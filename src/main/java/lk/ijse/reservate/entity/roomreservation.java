package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class roomreservation {
    private String CheckIn;
    private String CheckOut;
    private String RoomReservationId;
    private String GuestId;
    private String RoomNumber;
}