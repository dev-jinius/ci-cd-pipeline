package com.jinius.ecommerce.user.domain.service;

import com.jinius.ecommerce.user.domain.model.UserPointDto;
import com.jinius.ecommerce.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 유저 포인트 저장(업데이트)
 */
@Component
@RequiredArgsConstructor
public class UpdateUserPointService {

    private final UserRepository userRepository;

    /**
     * 포인트 저장
     * @param user UserPointDto
     * @return UserPointDto
     */
    public UserPointDto updatePoint(UserPointDto user) {
        return userRepository.savePoint(user);
    }
}
