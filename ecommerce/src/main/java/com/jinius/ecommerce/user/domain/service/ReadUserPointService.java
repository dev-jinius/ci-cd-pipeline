package com.jinius.ecommerce.user.domain.service;

import com.jinius.ecommerce.common.ApiException;
import com.jinius.ecommerce.common.ErrorCode;
import com.jinius.ecommerce.user.domain.model.UserPointDto;
import com.jinius.ecommerce.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 유저, 포인트 조회
 */
@Component
@RequiredArgsConstructor
public class ReadUserPointService {

    private final UserRepository userRepository;

    /**
     * 포인트 조회
     * @param userId
     * @return
     */
    public UserPointDto getUser(Long userId) {
        Optional<UserPointDto> user = userRepository.findUserPoint(userId);
        return user.orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_USER));
    }
}
