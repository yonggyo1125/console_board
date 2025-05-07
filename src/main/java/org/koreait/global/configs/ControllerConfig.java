package org.koreait.global.configs;

import org.koreait.board.controllers.*;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.board.services.BoardDeleteService;
import org.koreait.board.services.BoardInfoService;
import org.koreait.board.services.BoardSaveService;
import org.koreait.board.validators.BoardSaveValidator;
import org.koreait.global.services.Bean;
import org.koreait.global.services.ServiceContainer;
import org.koreait.main.controllers.MainController;
import org.koreait.member.controllers.*;
import org.koreait.member.services.MemberJoinService;
import org.koreait.member.services.MemberLoginService;
import org.koreait.member.services.MemberUpdateService;

public class ControllerConfig {

    /**
     * 첫 화면
     *
     * @return
     */
    public MainController mainController() {
        return new MainController();
    }
    /* 회원 관련 S */
    /**
     * 회원 메인 컨트롤러
     *
     * @return
     */
    public MemberController memberController() {
        return new MemberController();
    }

    // 회원 가입
    public MemberJoinController memberJoinController() {
        MemberJoinService service = ServiceContainer.getBean(MemberJoinService.class);

        return new MemberJoinController(service);
    }

    // 회원 로그인
    public MemberLoginController memberLoginController() {
        MemberLoginService service = ServiceContainer.getBean(MemberLoginService.class);

        return new MemberLoginController(service);
    }

    // 회원 정보
    public MemberInfoController memberInfoController() {
        return new MemberInfoController();
    }

    // 회원 정보 수정
    public MemberUpdateController memberUpdateController() {
        MemberUpdateService service = ServiceContainer.getBean(MemberUpdateService.class);

        return new MemberUpdateController(service);
    }

    // 회원 목록(관리자용)
    public MemberAdminController memberAdminController() {
        return new MemberAdminController();
    }
    /* 회원 관련 E */
    /**
     * 게시글 작성, 수정, 조회 관련 컨트롤러
     *
     * @return
     */
    public BoardController boardController() {
        return new BoardController();
    }

    public BoardWriteController boardWriteController() {
        BoardSaveService service = ServiceContainer.getBean(BoardSaveService.class);
        return new BoardWriteController(service);
    }

    @Bean
    public BoardListController boardListController() {
        BoardInfoService service = ServiceContainer.getBean(BoardInfoService.class);
        return new BoardListController(service);
    }

    @Bean
    public BoardViewController boardViewController() {
        BoardInfoService service = ServiceContainer.getBean(BoardInfoService.class);
        BoardDeleteService deleteService = ServiceContainer.getBean(BoardDeleteService.class);
        return new BoardViewController(service, deleteService);
    }

    @Bean
    public BoardUpdateController boardUpdateController() {
        BoardInfoService infoService = ServiceContainer.getBean(BoardInfoService.class);
        BoardSaveService saveService = ServiceContainer.getBean(BoardSaveService.class);
        return new BoardUpdateController(infoService, saveService);
    }
}
