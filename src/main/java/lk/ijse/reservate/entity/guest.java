package lk.ijse.reservate.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class guest {
    private String GuestId;
    private String UserId;
    private String Nic;
    private String Fullname;
    private String Address;
    private String Mobile;
    private String Date;
    private String Email;
}
