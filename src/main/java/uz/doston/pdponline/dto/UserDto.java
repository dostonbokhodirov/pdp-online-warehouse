package uz.doston.pdponline.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class UserDto {
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private List<Integer> warehousesId;
}
