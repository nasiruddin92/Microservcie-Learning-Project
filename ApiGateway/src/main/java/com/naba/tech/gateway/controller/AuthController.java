package com.naba.tech.gateway.controller;


import com.naba.tech.gateway.models.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger= LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    ResponseEntity<ApiResponse> login(
         @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient authorizedClient,
         @AuthenticationPrincipal OidcUser user,
         Model model
    ){
        logger.info( "User Email: {}",user.getEmail());

        List<String> authorities = user.getAuthorities().stream().map( grantAuthorities -> {
            return ((GrantedAuthority) grantAuthorities).getAuthority();
        } ).collect( Collectors.toList() );

        ApiResponse apiResponse=ApiResponse.builder()
                .useId(user.getEmail())
                .accessToken(authorizedClient.getAccessToken().getTokenValue())
                .refreshToken(authorizedClient.getRefreshToken().getTokenValue())
                .expireAt(authorizedClient.getAccessToken().getExpiresAt().getEpochSecond())
                .authorities(authorities)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }



}
