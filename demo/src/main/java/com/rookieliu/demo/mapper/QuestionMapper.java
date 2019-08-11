package com.rookieliu.demo.mapper;

import com.rookieliu.demo.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tags) " +
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tags})")
    void create(Question question);

//    @Results(id = "questionList",value = {
//                    @Result(property = "gmtCreate",column = "gmt_create"),
//                    @Result(property = "gmtModified",column = "gmt_modified"),
//                    @Result(property = "commentCount",column = "comment_count"),
//                    @Result(property = "viewCount",column = "view_count"),
//                    @Result(property = "likeCount",column = "like_count"),
//    })
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();
}
