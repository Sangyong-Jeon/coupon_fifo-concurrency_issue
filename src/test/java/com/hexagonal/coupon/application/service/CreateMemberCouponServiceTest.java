package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.CreateMemberCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberCouponUseCase;
import com.hexagonal.coupon.application.port.in.DeleteCouponUseCase;
import com.hexagonal.coupon.application.port.in.FindCouponResponse;
import com.hexagonal.coupon.application.port.in.FindCouponUseCase;
import com.hexagonal.coupon.application.port.out.CreateCouponPort;
import com.hexagonal.coupon.domain.Coupon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateMemberCouponServiceTest {

    @Autowired
    private CreateMemberCouponUseCase createMemberCouponUseCase;

    @Autowired
    private FindCouponUseCase findCouponUseCase;

    @Autowired
    private CreateCouponPort createCouponPort;

    @Autowired
    private DeleteCouponUseCase deleteCouponUseCase;

    private long testCouponId = 12345L;
    private final String TEST_COUPON_NAME = "테스트쿠폰";

    @BeforeAll
    void 쿠폰_생성() {
        createCouponPort.createCoupon(new Coupon(testCouponId, TEST_COUPON_NAME, 1000, 20000, 20000));
        testCouponId = findCouponUseCase.findCouponByName(TEST_COUPON_NAME).getId();
    }

    @AfterAll
    void 쿠폰_삭제() {
        deleteCouponUseCase.deleteCoupon(testCouponId);
    }

    @Test
    @Order(1)
    void 쿠폰_수량_확인() {
        final int remainQuantity = 20000;
        final int currentRemainQuantity = findCouponUseCase.findCouponByName(TEST_COUPON_NAME).getRemainQuantity();
        assertEquals(remainQuantity, currentRemainQuantity);
    }

    @Test
    @Order(2)
    void 여러회원_쿠폰_발급() throws InterruptedException {
        //given
        final int remainQuantity = 10000;
        final int memberCount = 10000;
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        List<Integer> memberIds = IntStream.rangeClosed(1, memberCount).boxed().collect(Collectors.toList());
        List<CompletableFuture<Void>> createMemberCouponFutures = memberIds.stream()
                .map(memberId -> CompletableFuture.runAsync(() -> {
                    createMemberCouponUseCase.createMemberCoupon(new CreateMemberCouponCommand((long) memberId, testCouponId));
                }, executorService))
                .collect(Collectors.toList());

        //when
        createMemberCouponFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        executorService.shutdown();
        executorService.awaitTermination(300, TimeUnit.SECONDS);

        //then
        final int currentRemainQuantity = findCouponUseCase.findCouponByName(TEST_COUPON_NAME).getRemainQuantity();
        assertEquals(remainQuantity, currentRemainQuantity);
    }
}