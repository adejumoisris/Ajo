package com.AjoPay.AjoPay.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class UserRequestDto {
    @NotBlank(message = "firstname is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @Email(message = "invalid Email, please check input")
    private String email;
    @Min(11)
    @Max(11)
    private Integer phoneNumber;

//    private String password;
//
//    private String confirmPassword;
//
//    private String gender;

}