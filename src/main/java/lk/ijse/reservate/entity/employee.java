package lk.ijse.reservate.entity;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class employee {
    private String EmpId;
    private String Nic;
    private String Fullname;
    private String Address;
    private String Mobile;
    private Date   Date;
    private String JobRole;
    private String Email;

}
