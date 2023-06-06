package lk.ijse.reservate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class RoomDTO {
    private String RoomNumber;
    private String RoomType;
    private Double Price;
    private String Status;
}
