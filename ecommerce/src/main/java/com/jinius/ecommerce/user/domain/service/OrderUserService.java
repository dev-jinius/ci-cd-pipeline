package com.jinius.ecommerce.user.domain.service;

import com.jinius.ecommerce.common.ApiException;
import com.jinius.ecommerce.common.ErrorCode;
import com.jinius.ecommerce.user.domain.model.UserPointDto;
import com.jinius.ecommerce.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderUserService {

    private final UserRepository userRepository;

    /**
     * 포인트 조회
     * @param userId
     * @return
     */
    public UserPointDto getOrderUsePoint(Long userId) {
        Optional<UserPointDto> user = userRepository.findUserPoint(userId);
        return user.orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_USER));
    }

    /**
     * 포인트 차감
     * @param userPointDto
     * @param price
     * @return UserPointDto
     */
    public UserPointDto pointProcess(UserPointDto userPointDto, BigInteger price) {
        userPointDto.subtractPoint(price);
        return userRepository.savePoint(userPointDto);
    }
}
