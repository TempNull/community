package com.rookieliu.demo.controller;

import com.rookieliu.demo.dto.AccessTokenDTO;
import com.rookieliu.demo.dto.GithubUser;
import com.rookieliu.demo.mapper.UserMapper;
import com.rookieliu.demo.model.User;
import com.rookieliu.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    public GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.redirectUri}")
    private String clientRedirectUri;


    @Autowired
    public UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(clientRedirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null && githubUser.getId() != null){
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setBio(githubUser.getBio());
            user.setName(githubUser.getName());
            user.setLogin(githubUser.getLogin());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());

            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            //如果登录成功，写入session和cookie
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            //登录失败，重新登录
            return "redirect:/";

        }

    }
}
