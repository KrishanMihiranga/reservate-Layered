package lk.ijse.reservate.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class roomReservationDTO {
    private String CheckIn;
    private String CheckOut;
    private String RoomReservationId;
    private String GuestId;
    private String RoomNumber;
}
