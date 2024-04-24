package com.jinius.ecommerce.user.domain;

import com.jinius.ecommerce.user.domain.model.UserPointDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * 유저 포인트 기능 테스트
 */
class UserPointDtoTest {

    /**
     * 포인트 충전
     */
    @Test
    @DisplayName("포인트 충전 요청 시 원래의 포인트에서 요청한 포인트를 더한다.")
    void addPoint() {
        //gien
        Long userId = 1L;

        BigInteger originPoint = BigInteger.ZERO;
        UserPointDto testUser = new UserPointDto(userId, originPoint);
        BigInteger requestPoint = BigInteger.valueOf(30000);
        UserPointDto request = new UserPointDto(userId, requestPoint);

        //when
        testUser.addPoint(requestPoint);

        //then
        assert testUser.getPoint().compareTo(originPoint.add(requestPoint)) == 0;
    }
}