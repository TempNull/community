package com.rookieliu.demo.dto;

import com.rookieliu.demo.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String description;
    private String title;
    private String tags;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
