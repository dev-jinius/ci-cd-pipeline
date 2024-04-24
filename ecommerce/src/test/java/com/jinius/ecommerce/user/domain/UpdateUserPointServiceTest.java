package com.jinius.ecommerce.user.domain;

import com.jinius.ecommerce.user.domain.model.UserPointDto;
import com.jinius.ecommerce.user.domain.repository.UserRepository;
import com.jinius.ecommerce.user.domain.service.UpdateUserPointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * 포인트 저장 및 업데이트 테스트
 */
@ExtendWith(MockitoExtension.class)
class UpdateUserPointServiceTest {

    @InjectMocks
    UpdateUserPointService sut;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("포인트 충전 요청 시 기존 유저의 포인트에 충전 금액을 더하고, 그대로 반환한다.")
    void chargePoint() {
        //gien
        Long userId = 1L;
        BigInteger requestPoint = BigInteger.valueOf(30000);
        BigInteger originPoint = BigInteger.ZERO;
        UserPointDto originUser = new UserPointDto(userId, originPoint);

        //when
        when(userRepository.findUserPoint(userId)).thenReturn(Optional.of(originUser));
        UserPointDto testUser = userRepository.findUserPoint(userId).get();
        testUser.addPoint(requestPoint);

        when(userRepository.savePoint(testUser)).thenReturn(testUser);
        UserPointDto result = sut.updatePoint(testUser);

        //then
        assert result.getUserId() == originUser.getUserId();
        assert result.getPoint().compareTo(BigInteger.valueOf(30000)) == 0;
        assert result.getPoint().compareTo(originPoint.add(requestPoint)) == 0;
    }
}