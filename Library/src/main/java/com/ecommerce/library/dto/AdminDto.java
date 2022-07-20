package com.ecommerce.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    @Size(min = 3, max = 10, message = "Invalide prénom (3-10 caracteres")
    private String firstName;
    @Size(min = 3, max = 10, message = "Invalide prénom (3-10 caracteres")
    private String lastName;

    private String username;
    @Size(min = 5, max = 10, message = "Invalide prénom (3-10 caracteres")

    private String password;

    private String repeatPassword;


}
