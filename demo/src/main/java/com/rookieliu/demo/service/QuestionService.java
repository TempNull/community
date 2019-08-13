package com.rookieliu.demo.service;

import com.rookieliu.demo.dto.PaginationDTO;
import com.rookieliu.demo.dto.QuestionDTO;
import com.rookieliu.demo.mapper.QuestionMapper;
import com.rookieliu.demo.mapper.UserMapper;
import com.rookieliu.demo.model.Question;
import com.rookieliu.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        Integer totalPage;
        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;

        page = page < 1 ? 1 : page;
        page = page > totalPage ? totalPage  : page;
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);



        return paginationDTO;
    }

    public PaginationDTO list(String accountId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserAccountId(Integer.valueOf(accountId));
        Integer totalPage;
        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;

        page = page < 1 ? 1 : page;
        page = page > totalPage ? totalPage  : page;
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserAccountId(Integer.valueOf(accountId),offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);



        return paginationDTO;
    }
}
