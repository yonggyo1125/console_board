package org.koreait.board.controllers;

import org.koreait.board.entities.BoardData;
import org.koreait.board.exceptions.BoardNotFoundException;
import org.koreait.board.services.BoardDeleteService;
import org.koreait.board.services.BoardInfoService;
import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class BoardViewController extends Controller {
    // 게시글 수정을 위한 게시글 번호
    private static long seq; // 게시글 번호

    private final BoardInfoService service;
    private final BoardDeleteService deleteService;

    public BoardViewController(BoardInfoService service, BoardDeleteService deleteService) {
        this.service = service;
        this.deleteService = deleteService;
        setMenus(List.of("1", "2", "3"));
    }

    public static void setSeq(long seq) {
        BoardViewController.seq = seq;
    }

    @Override
    public void show() {
        if (seq < 1L) throw new BoardNotFoundException();
        // 게시글 조회
        service.updateMapper();
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
        System.out.println("1. 수정, 2. 삭제, 3. 목록");
    }

    @Override
    public void common() {
        System.out.println("************** 게시글 보기 ***************");
    }

    @Override
    public void process(String command) {
       int menu = Integer.parseInt(command);
       switch(menu) {
           case 1: // 게시글 수정
                BoardUpdateController.setSeq(seq);
                Router.change(BoardUpdateController.class);
                break;
           case 2: // 게시글 삭제
                deleteService.process(seq);
               System.out.println("게시글이 삭제 되었습니다. 목록으로 이동합니다.");


           case 3: // 게시글 목록
               Router.change(BoardListController.class);
               break;

       }
    }
}
