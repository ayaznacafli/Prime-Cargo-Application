package com.prime.user.service.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private Set<String> roles;

    public JwtResponse(String accessToken, Long id, String email, Set<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    @JsonProperty("token")
    String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
