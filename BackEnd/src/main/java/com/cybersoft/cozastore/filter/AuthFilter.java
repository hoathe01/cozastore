package com.cybersoft.cozastore.filter;

import com.cybersoft.cozastore.util.JwtHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;
    private Gson gson = new Gson();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String data = jwtHelper.validToken(token);
            if (data != null && !data.isEmpty()) {
                Type listRole = new TypeToken<ArrayList<SimpleGrantedAuthority>>() {}.getType();
                List<GrantedAuthority> role = gson.fromJson(data,listRole);
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken("","",role);

                SecurityContextHolder.getContext().setAuthentication(user);

            }
        }
        filterChain.doFilter(request,response);
    }
}
