package com.rookieliu.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class quick3 {
    @Value("${person.name}")
    private String name;


    @RequestMapping("/quick3")
    @ResponseBody

    public String quick3(){
        return "name" + name;
    }
}
