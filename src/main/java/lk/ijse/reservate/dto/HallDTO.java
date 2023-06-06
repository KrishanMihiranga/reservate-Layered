package lk.ijse.reservate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HallDTO {
    private String HallNumber;
    private String UserId;
    private String HallType;
    private Double Price;
    private String Status;

}
