package com.mwas.Attendance1.Security;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class SuccessHandler implements AuthenticationSuccessHandler  {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication.getAuthorities().stream()
                .anyMatch(role->role.equals("ROLE_ADMIN"))){
            response.sendRedirect("/admin/home");
        }else {
            response.sendRedirect("/user/home");
        }

    }
}
