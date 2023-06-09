package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class hall {
    private String HallNumber;
    private String UserId;
    private String HallType;
    private Double Price;
    private String Status;

}
