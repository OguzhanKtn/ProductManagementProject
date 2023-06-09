package com.works.vize_1.configs;


import com.works.vize_1.entities.User;
import com.works.vize_1.services.TinkEncDec;
import com.works.vize_1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final TinkEncDec tinkEncDec;
    final UserService service;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        String freeUrls[] = {"/","/login","/search","/loginUser","/detail"};
        boolean loginStatus = true;
        for(String item : freeUrls){
            if(url.contains(item)){
                loginStatus = false;
                break;
            }
        }
       if(loginStatus){
           if(request.getCookies() !=null){
               Cookie[] cookies = request.getCookies();
               for(Cookie cookie : cookies){
                   if(cookie.getName().equals("user")){
                       String plainText = tinkEncDec.decrypt(cookie.getValue());
                       Long val = Long.parseLong(plainText);
                       User u = service.single(val);
                       System.out.println(u);
                       if(u != null){
                           request.getSession().setAttribute("user",u);
                           break;
                       }
                   }
               }
           }
           boolean status = request.getSession().getAttribute("user") == null;
           if(status){
               response.sendRedirect("/login");
           }else{
               User user  = (User) request.getSession().getAttribute("user");
               System.out.println(user);
               request.setAttribute("user",user);
               filterChain.doFilter(request,response);
           }
       }else{
           filterChain.doFilter(request,response);
       }
    }
}
