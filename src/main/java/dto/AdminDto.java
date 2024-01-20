package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminDto {
    private String adminId;
    private String email;
    private String password;
}
