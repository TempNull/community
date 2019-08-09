package com.rookieliu.demo.mapper;

import com.rookieliu.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tags) " +
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tags})")
    void create(Question question);
}
