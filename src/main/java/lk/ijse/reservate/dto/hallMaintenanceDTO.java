package lk.ijse.reservate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class hallMaintenanceDTO {
    private String HallMaintenanceId;
    private String Date;
    private String startTime;
    private String EndTime;
    private String HallNumber;
}
