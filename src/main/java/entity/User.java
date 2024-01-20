package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String userId;
    private String name;
    private String email;
    private String address;
    private int contactNumber;
    private String newPassword;

}


