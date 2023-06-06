package lk.ijse.reservate.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuestDTO {
    private String GuestId;
    private String UserId;
    private String Nic;
    private String Fullname;
    private String Address;
    private String Mobile;
    private String Date;
    private String Email;
}
