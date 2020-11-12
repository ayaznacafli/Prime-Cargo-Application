package com.prime.service.payload.request;

import com.prime.validation.email.ValidEmail;
import com.prime.validation.password.PasswordMatches;
import com.prime.validation.password.ValidPassword;
import lombok.Data;


@Data
@PasswordMatches(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SigninRequest {

    private String username;
    private String firstName;
    private String lastName;
    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    private String matchingPassword;


}
