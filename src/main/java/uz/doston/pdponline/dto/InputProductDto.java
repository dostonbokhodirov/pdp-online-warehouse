package uz.doston.pdponline.dto;

import lombok.Data;

@Data
public class InputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private String expireDate;

    private Integer inputId;
}
