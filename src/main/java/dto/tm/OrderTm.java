package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderTm extends RecursiveTreeObject<OrderTm> {
    private String orderId;
    private String customerName;
    private String itemCategory;
    private String itemName;
    private int itemQty;
    private double itemPrice;
    private String date;
    private JFXButton btn;

}
