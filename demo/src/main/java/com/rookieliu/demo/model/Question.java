package com.rookieliu.demo.model;

import lombok.Data;
import sun.jvm.hotspot.utilities.IntArray;

@Data
public class Question {
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


}
