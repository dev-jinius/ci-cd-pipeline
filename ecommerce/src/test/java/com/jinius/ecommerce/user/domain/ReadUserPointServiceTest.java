package com.jinius.ecommerce.user.domain;

import com.jinius.ecommerce.common.ApiException;
import com.jinius.ecommerce.common.ErrorCode;
import com.jinius.ecommerce.user.domain.model.UserPointDto;
import com.jinius.ecommerce.user.domain.repository.UserRepository;
import com.jinius.ecommerce.user.domain.service.ReadUserPointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * 포인트 조회 테스트
 */
@ExtendWith(MockitoExtension.class)
class ReadUserPointServiceTest {

    @InjectMocks
    ReadUserPointService sut;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("유저 포인트 조회 성공")
    void getUser() {
        //given
        Long userId = 1L;

        //when
        when(userRepository.findUserPoint(any())).thenReturn(Optional.of(new UserPointDto(userId, any())));
        UserPointDto user = sut.getUser(userId);

        //then
        assert user.getUserId() == userId;
    }

    @Test
    @DisplayName("유저가 DB에 없는 경우, Exception(NOT_FOUND_USER) 반환")
    void NOT_FOUND_USER() {
        //given
        Long userId = 1L;

        //when
        when(userRepository.findUserPoint(any())).thenReturn(Optional.empty());
        Throwable exception = null;
        try {
            UserPointDto user = sut.getUser(userId);
        } catch (ApiException e) {
            exception = e;
        }
        //then
        assert exception != null;
        assert exception instanceof ApiException;
        assert ((ApiException) exception).getErrorCode() == ErrorCode.NOT_FOUND_USER;
        assert ((ApiException) exception).getErrorCode().getMessage().equals("일치하는 유저가 없습니다.");
    }
}