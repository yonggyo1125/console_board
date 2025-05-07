package org.koreait.board.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.global.services.ServiceContainer;

public class BoardDeleteServiceTest {
    private BoardDeleteService service;

    @BeforeEach
    void init() {
        service = ServiceContainer.getBean(BoardDeleteService.class);
    }

    @Test
    void test1() {
        service.process(2L);
    }
}
