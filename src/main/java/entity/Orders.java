package entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {
    private String orderId;
    private String itemCategory;
    private String itemName;
    private int itemQty;
    private double itemPrice;
    private String orderDate;
    private String customerId;
}
