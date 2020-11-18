package com.prime.user.service.payload.request;

import com.prime.user.validation.email.ValidEmail;
import com.prime.user.validation.password.PasswordMatches;
import com.prime.user.validation.password.ValidPassword;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
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
