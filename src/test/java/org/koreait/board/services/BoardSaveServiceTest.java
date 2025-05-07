package org.koreait.board.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.board.controllers.RequestBoard;
import org.koreait.global.services.ServiceContainer;
import org.koreait.member.MemberSession;
import org.koreait.member.entities.Member;
import org.koreait.member.services.MemberInfoService;

public class BoardSaveServiceTest {

    private BoardSaveService service;

    @BeforeEach
    void init() {
        // 로그인 처리 후
        MemberInfoService infoService = ServiceContainer.getBean(MemberInfoService.class);
        Member member = infoService.get("user01@test.org");
        MemberSession.setMember(member);

        service = ServiceContainer.getBean(BoardSaveService.class);
    }

    @Test
    @DisplayName("등록 테스트")
    void test1() {

        RequestBoard form = new RequestBoard();
        form.setPoster("작성자");
        form.setSubject("제목");
        form.setContent("내용");

        service.process(form);
    }

    @Test
    @DisplayName("등록 테스트")
    void test2() {

        RequestBoard form = new RequestBoard();
        form.setSeq(1L);
        form.setPoster("(수정)작성자");
        //form.setSubject("제목");
        //form.setContent("내용");

        service.process(form);
    }
}
