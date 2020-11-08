package com.prime.service.payload.request;

import com.prime.config.PasswordConstants;
import com.prime.validation.password.PasswordMatches;
import com.prime.validation.password.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatches(first = "newPassword", second = "matchingNewPassword", message = "The password fields must match")
public class PasswordRequest {

    @NotNull
    private String oldPassword;

    @ValidPassword
    @NotNull
    private String newPassword;

    @NotNull
    private String matchingNewPassword;

}
