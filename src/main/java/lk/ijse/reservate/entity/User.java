package lk.ijse.reservate.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String UserId;
    private String EmpId;
    private String UserName;
    private String Password;
}
