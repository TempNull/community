package com.rookieliu.demo.controller;

import com.rookieliu.demo.mapper.QuestionMapper;
import com.rookieliu.demo.mapper.UserMapper;
import com.rookieliu.demo.model.Question;
import com.rookieliu.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tags",required = false) String tags,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        if(title == null || title == ""){
            model.addAttribute("error", "标题不能为空");
            return "publish";

        }
        if(description == null || description == ""){
            model.addAttribute("error", "描述不能为空");
            return "publish";

        }
        if(tags == null || tags == ""){
            model.addAttribute("error", "标签不能为空");
            return "publish";

        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        System.out.println(user.getAccountId());
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTags(tags);
        question.setCreator(Integer.valueOf(user.getAccountId()));
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());



        questionMapper.create(question);
        return "redirect:/";
    }
}
