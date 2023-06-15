package lk.ijse.reservate.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class hallmaintenance {
    private String HallMaintenanceId;
    private Date date;
    private String startTime;
    private String EndTime;
    private String HallNumber;
}
