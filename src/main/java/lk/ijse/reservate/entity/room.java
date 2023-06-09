package lk.ijse.reservate.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class room {
    private String RoomNumber;
    private String RoomType;
    private Double Price;
    private String Status;
}
