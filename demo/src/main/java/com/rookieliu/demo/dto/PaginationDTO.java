package com.rookieliu.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;

        page = page < 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        showPrevious = page != 1;
        showNext = page != totalPage;
        showFirstPage = !pages.contains(1);
        showLastPage = !pages.contains(totalPage);


    }
}
