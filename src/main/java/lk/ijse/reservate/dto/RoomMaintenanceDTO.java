package lk.ijse.reservate.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomMaintenanceDTO {
    private String RoomMaintenanceId;
    private Date date;
    private String startTime;
    private String EndTime;
    private String RoomNumber;
}
