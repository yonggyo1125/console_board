package org.koreait.board.controllers;

import org.koreait.board.entities.BoardData;
import org.koreait.board.exceptions.BoardNotFoundException;
import org.koreait.board.services.BoardInfoService;
import org.koreait.global.router.Controller;

import java.time.format.DateTimeFormatter;

public class BoardViewController extends Controller {

    private BoardInfoService service;

    public BoardViewController(BoardInfoService service) {
        this.service = service;
    }

    // 게시글 수정을 위한 게시글 번호
    private static long seq; // 게시글 번호

    public static void setSeq(long seq) {
        BoardViewController.seq = seq;
    }

    @Override
    public void show() {
        if (seq < 1L) throw new BoardNotFoundException();
        // 게시글 조회
        BoardData item = service.get(seq);

        /* 게시글 내용 출력 */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        System.out.printf("작성일시: %s%n", formatter.format(item.getRegDt()));
        System.out.printf("작성자: %s%n", item.getPoster());
        System.out.printf("제목: %s%n", item.getSubject());
        printLine();
        System.out.println(item.getContent()); // 내용 출력
        printLine();

        System.out.println("처리할 메뉴를 선택하세요.");
        System.out.println("1. 수정, 2. 삭제");
    }

    @Override
    public void common() {
        System.out.println("************** 게시글 보기 ***************");
    }

    @Override
    public void process(String command) {
        super.process(command);
    }
}
