package org.koreait.board.controllers;

import org.koreait.board.entities.BoardData;
import org.koreait.board.services.BoardInfoService;
import org.koreait.global.exceptions.CommonException;
import org.koreait.global.paging.SearchForm;
import org.koreait.global.router.Controller;
import org.koreait.global.router.Router;

import java.util.List;
import java.util.Scanner;

public class BoardListController extends Controller {
    private final BoardInfoService service;
    private List<BoardData> items;

    public BoardListController(BoardInfoService service) {
        this.service = service;

        // 초기 출력할 게시글 조회
        SearchForm s = new SearchForm();
        items = service.getList(s);

        Scanner sc = new Scanner(System.in);
        setPrompt(() -> {
            SearchForm search = new SearchForm();

            while(true) {
                try {
                    System.out.println("조회할 항목을 선택하세요.");
                    System.out.println("1. 제목, 2. 내용, 3. 작성자, 4. 통합검색, 5. 게시글 보기");
                    String sel = inputEach("1. 항목번호", sc);
                    // 선택항목 1, 2, 3, 4 중에서만 선택가능
                    if (!List.of("1","2", "3", "4", "5").contains(sel)) {
                        continue;
                    }
                    String sopt = null;
                    int menu = Integer.parseInt(sel);
                    switch (menu) {
                        case 1: // 제목
                            sopt = "subject"; break;
                        case 2: // 내용
                            sopt = "content"; break;
                        case 3: // 작성자
                            sopt = "poster"; break;
                    }

                    search.setSopt(sopt);

                    String skey = inputEach(menu == 5 ? "2. 게시글번호": "2. 검색키워드", sc);
                    search.setSkey(skey);

                    if (menu == 5) { // 게시글 보기 화면으로 이동
                        try {
                            // 게시글 번호 등록
                            long seq = Integer.parseInt(skey);
                            BoardViewController.setSeq(seq);
                            Router.change(BoardViewController.class);
                        } catch (NumberFormatException e) {
                            System.out.println("게시글 번호는 숫자로 입력하세요.");
                        }
                        return;
                    }
                    items = service.getList(search);
                    show(); // 화면 갱신
                } catch (CommonException e) {
                    printError(e);
                }
            }
        });
    }


    @Override
    public void show() {
        printLine();
        System.out.println("게시글번호|작성자|제목|내용");
        if (items == null || items.isEmpty()) {
            System.out.println("조회된 게시글이 없습니다.");
        } else { // 게시글 출력
            items.forEach(i -> {
                System.out.printf("%d|%s|%s|%s%n", i.getSeq(), i.getPoster(), i.getSubject(), i.getContent());
            });
        }
        printLine();
    }

    @Override
    public void common() {
        System.out.println("************** 게시글 목록 ***************");
    }
}
