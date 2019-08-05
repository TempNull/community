package com.rookieliu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class quickController {
    @RequestMapping("/quick")
    @ResponseBody
    public String qucik(){
        return "nihao stringboot!~";
    }
}
