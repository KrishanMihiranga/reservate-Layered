package lk.ijse.reservate.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

public class roommaintenance {
    private String RoomMaintenanceId;
    private Date date;
    private String startTime;
    private String EndTime;
    private String RoomNumber;
}
