package org.koreait.board.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.board.entities.BoardData;
import org.koreait.global.paging.SearchForm;
import org.koreait.global.services.ServiceContainer;

import java.util.List;

public class BoardInfoServiceTest {

    private BoardInfoService service;

    @BeforeEach
    void init() {
        service = ServiceContainer.getBean(BoardInfoService.class);
    }

    @Test
    @DisplayName("조회 테스트")
    void test1() {
        BoardData item = service.get(1L);
        System.out.println(item);

        SearchForm search = new SearchForm();
        List<BoardData> items = service.getList(search);
        items.forEach(System.out::println);
    }
}
