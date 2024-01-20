package dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDto {
    private String customerId;
    private String customerName;
    private String address;
    private int contactNumber;
    private String email;
}


