package com.rookieliu.demo.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String login;
    private String accountId;
    private String token;
    private String bio;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
