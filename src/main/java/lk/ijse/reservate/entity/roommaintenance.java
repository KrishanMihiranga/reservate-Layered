package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

public class roommaintenance {
    private String RoomMaintenanceId;
    private String Date;
    private String startTime;
    private String EndTime;
    private String RoomNumber;
}
