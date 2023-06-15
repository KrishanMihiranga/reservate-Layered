package lk.ijse.reservate.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HallMaintenanceDTO {
    private String HallMaintenanceId;
    private Date date;
    private String startTime;
    private String EndTime;
    private String HallNumber;

}
