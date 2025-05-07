package org.koreait.board.services;

import org.koreait.board.controllers.RequestBoard;
import org.koreait.board.entities.BoardData;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.global.validators.Validator;
import org.koreait.member.MemberSession;
import org.koreait.member.entities.Member;

public class BoardSaveService {
    private BoardMapper mapper;
    private final Validator<RequestBoard> validator;

    public BoardSaveService(BoardMapper mapper, Validator<RequestBoard> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void process(RequestBoard form) {
        validator.check(form);

        BoardData item = new BoardData();
        // 공통 저장 항목
        item.setPoster(form.getPoster());
        item.setSubject(form.getSubject());
        item.setContent(form.getContent());

        /**
         * seq가 있으면 수정, 없으면 추가
         */
        if (form.getSeq() > 0L) { // 수정
            item.setSeq(form.getSeq());
            mapper.update(item);
        } else { // 추가
            // 회원정보는 수정 될 수 없고 추가시에만 등록
            Member member = MemberSession.getMember();
            item.setMemberSeq(member.getSeq());
            mapper.register(item);
        }


    }
}
