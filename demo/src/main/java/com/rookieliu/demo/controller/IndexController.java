package com.rookieliu.demo.controller;

import com.rookieliu.demo.mapper.UserMapper;
import com.rookieliu.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;

@Controller
public class IndexController {
    @Autowired
    public UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return "index";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        return "index";
    }
}
