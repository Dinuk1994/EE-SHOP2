package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTm extends RecursiveTreeObject<UserTm> {

    private String userId;
    private String name;
    private String email;
    private String address;
    private int contactNumber;
    private String primaryPassword;
    private JFXButton btn;

}
