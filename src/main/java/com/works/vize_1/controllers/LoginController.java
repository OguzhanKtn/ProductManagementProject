package com.works.vize_1.controllers;

import com.works.vize_1.entities.User;
import com.works.vize_1.services.TinkEncDec;
import com.works.vize_1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final HttpServletRequest request;
    final HttpServletResponse response;
    final UserService service;
    final TinkEncDec tinkEncDec;

    int status = 0;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("status",status);
        status = 0;
        return "login";
    }

    @PostMapping("/loginUser")
    public String login(User user){
        User u = service.userLogin(user);
        if(u != null){
            request.getSession().setAttribute("user",""+u.getUid());
            if(user.getRemember() != null && user.getRemember().equals("on")){
                String chipherText = tinkEncDec.encrypt(""+u.getUid());
                Cookie cookie = new Cookie("user",chipherText);
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
            }

            return "redirect:/dashboard";
        }
        status = 1;
       return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
