package com.rookieliu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class helloSpringMVC {
    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name")String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}