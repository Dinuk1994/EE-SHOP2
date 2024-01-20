package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailsDto {
    private String orderId;
    private String customerId;
    private String customerName;
    private String itemCategory;
    private String itemName;
    private int itemQty;
    private double itemPrice;
    private String status;

}

