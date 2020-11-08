package com.prime.service.payload.response;


import com.prime.service.JwtToken;
import lombok.Data;

import java.util.Set;

@Data
public class JwtResponse {
    private JwtToken token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private Set<String> roles;

    public JwtResponse(JwtToken accessToken, Long id, String email, Set<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}