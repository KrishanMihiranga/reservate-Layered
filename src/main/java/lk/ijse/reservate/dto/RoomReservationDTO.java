package lk.ijse.reservate.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomReservationDTO {
    private String CheckIn;
    private String CheckOut;
    private String RoomReservationId;
    private String GuestId;
    private String RoomNumber;
}
