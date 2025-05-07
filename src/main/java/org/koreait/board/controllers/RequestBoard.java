package org.koreait.board.controllers;

import lombok.Data;

@Data
public class RequestBoard {
    private long seq;// 게시글 번호, 수정시 필요
    private String poster;
    private String subject;
    private String content;
    private String name; // 회원명
    private String email; // 회원 이메일
}
