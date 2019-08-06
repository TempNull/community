package com.rookieliu.demo.controller;

import com.rookieliu.demo.dto.AccessTokenDTO;
import com.rookieliu.demo.dto.GithubUser;
import com.rookieliu.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    public GithubProvider githubProvider;
    



    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("d09b9fa6cf69a2f49047");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret("ca3f6c3ab484481502968fb892c7a6c07aa5b1f6");
        accessTokenDTO.setRedirect_uri("http://localhost:8808/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getId());
        return "index";
    }
}
