package org.koreait.board.services;

import org.koreait.board.mappers.BoardMapper;
import org.koreait.board.validators.BoardSaveValidator;
import org.koreait.global.configs.DBConn;
import org.koreait.global.services.Bean;
import org.koreait.global.services.Configuration;
import org.koreait.global.services.ServiceContainer;

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

    @Bean
    public BoardInfoService boardInfoService() {
        return new BoardInfoService();
    }

    @Bean
    public BoardDeleteService boardDeleteService() {
        BoardMapper mapper = DBConn.getInstance().getSession().getMapper(BoardMapper.class);
        return new BoardDeleteService(mapper);
    }
}
