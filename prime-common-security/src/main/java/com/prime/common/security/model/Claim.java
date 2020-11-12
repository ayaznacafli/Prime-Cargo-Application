package com.prime.common.security.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Claim {

    private String key;
    private Set<String> claims;

}
