package org.koreait.board.services;

import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.validators.RequiredFieldValidator;

public class BoardDeleteService implements RequiredFieldValidator {
    private final BoardMapper mapper;

    public BoardDeleteService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    public void process(long seq) {
        // 게시글이 등록되었는지 여부 체크
        checkTrue(mapper.exists(seq) == 1, "게시글을 찾을 수 없습니다.");

        mapper.delete(seq);
    }
}
