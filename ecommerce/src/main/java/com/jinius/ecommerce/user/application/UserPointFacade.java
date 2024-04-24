package com.jinius.ecommerce.user.application;

import com.jinius.ecommerce.user.domain.model.UserPointDto;
import com.jinius.ecommerce.user.domain.service.ReadUserPointService;
import com.jinius.ecommerce.user.domain.service.UpdateUserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 유저 포인트 Pacade Service
 */
@Service
@RequiredArgsConstructor
public class UserPointFacade {

    private final ReadUserPointService readUserPointService;
    private final UpdateUserPointService updateUserPointService;

    /**
     * 유저 포인트 조회
     * @param userId
     * @return UserResponse
     */
    public UserResponse userPoint(Long userId) {
        UserPointDto result = readUserPointService.getUser(userId);
        return UserResponse.of(result);
    }
    /**
     * 유저 포인트 충전
     * @param request UserRequest
     * @return UserResponse
     */
    public UserResponse chargePoint(UserRequest request) {
        UserRequest.validate(request);
        UserPointDto user = userPoint(request.getUserId()).toUserPointDto();    //유저 조회
        user.addPoint(request.getPoint());  //충전
        UserPointDto result = updateUserPointService.updatePoint(user);
        return UserResponse.of(result);
    }
}
