package com.naba.tech.gateway.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String useId;
    private String accessToken;
    private String refreshToken;
    public long expireAt;
    private Collection<String> authorities;
}
