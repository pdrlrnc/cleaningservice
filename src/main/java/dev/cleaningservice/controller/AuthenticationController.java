package dev.cleaningservice.controller;

import dev.cleaningservice.entity.UserEntity;
import dev.cleaningservice.service.security.UserSecService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class AuthenticationController implements AuthenticationSuccessHandler {

    private UserSecService userSecService;

    public AuthenticationController(UserSecService userSecService){
        this.userSecService = userSecService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        System.out.println("ESTOU NO onAUTH\n\n\n\n");

        String username = authentication.getName();

        UserEntity userEntity = userSecService.findByUseName(username);

        HttpSession session = request.getSession();
        session.setAttribute("user", userEntity);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
