package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

public class CustomerTm extends RecursiveTreeObject<CustomerTm> {
    private String customerId;
    private String customerName;
    private String address;
    private int contactNumber;
    private String email;
    private JFXButton btn;

}
