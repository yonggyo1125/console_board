package org.koreait.board.exceptions;

import org.koreait.global.exceptions.NotFoundException;

public class BoardNotFoundException extends NotFoundException {
    public BoardNotFoundException() {
        super("게시글을 찾을 수 없습니다.");
    }
}
