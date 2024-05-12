package com.jinius.ecommerce.user.domain.repository;

import com.jinius.ecommerce.user.domain.model.UserPointDto;

import java.util.Optional;

public interface UserRepository {

    // 포인트 조회
    Optional<UserPointDto> findUserPoint(Long userId);

    //포인트 업데이트
    UserPointDto savePoint(UserPointDto userPointDto);
}
