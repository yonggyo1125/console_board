package org.koreait.board.services;

import org.koreait.board.mappers.BoardMapper;
import org.koreait.board.validators.BoardSaveValidator;
import org.koreait.global.configs.DBConn;
import org.koreait.global.services.Bean;
import org.koreait.global.services.Configuration;

@Configuration
public class BoardService {
    @Bean
    public BoardMapper boardMapper() {
        return DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    @Bean
    public BoardSaveValidator boardSaveValidator() {
        return new BoardSaveValidator();
    }

    @Bean
    public BoardSaveService saveService() {
        return new BoardSaveService(boardMapper(), boardSaveValidator());
    }
}
