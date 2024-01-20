package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private String UserId;
    private String name;
    private String email;
    private String address;
    private int contactNumber;
    private String primaryPassword;

}
