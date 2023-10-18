package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.util.JwtHelper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authenticationToken);
        List<GrantedAuthority> auth = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        String data = gson.toJson(auth);
        String token = jwtHelper.genToken(data);
        BaseResponse response = new BaseResponse(200,"",token);
        log.warn(auth.toString());
        log.warn("Bearer " + token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}