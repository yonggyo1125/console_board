package org.koreait.board.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.board.entities.BoardData;
import org.koreait.global.configs.DBConn;
import org.koreait.global.paging.SearchForm;

import java.util.List;

@DisplayName("BoardMapper 테스트")
public class MapperTest {

    private BoardMapper mapper;

    @BeforeEach
    void init() {
        mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    @Test
    @DisplayName("등록 테스트")
    void test1() {
        BoardData item = new BoardData();
        item.setMemberSeq(1L);
        item.setPoster("작성자1");
        item.setSubject("제목1");
        item.setContent("내용1");

        int affectedRows = mapper.register(item);
        System.out.println(affectedRows);
    }
    
    @Test
    @DisplayName("삭제, 조회 테스트")
    void test2() {
        mapper.delete(2L);

        BoardData item = mapper.get(2L).orElse(null);
        System.out.println(item);

        SearchForm search = new SearchForm();
        search.setOffset(0);
        search.setLimit(10);
        search.setSkey("제목");
        List<BoardData> items = mapper.getList(search);
        items.forEach(System.out::println);
    }


}
