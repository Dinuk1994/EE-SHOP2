package entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Customer {
    private String customerId;
    private String customerName;
    private String address;
    private int contactNumber;
    private String email;
}


