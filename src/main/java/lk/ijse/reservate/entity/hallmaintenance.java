package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class hallmaintenance {
    private String HallMaintenanceId;
    private String Date;
    private String startTime;
    private String EndTime;
    private String HallNumber;
}
