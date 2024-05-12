package com.jinius.ecommerce.user.domain.repository;

import com.jinius.ecommerce.user.domain.model.UserPointDto;
import com.jinius.ecommerce.user.infra.User;
import com.jinius.ecommerce.user.infra.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<UserPointDto> findUserPoint(Long userId) {
        return userJpaRepository.findById(userId).map(user -> user.toUserPointDomain());
    }

    @Override
    public UserPointDto savePoint(UserPointDto userPointDto) {
        return userJpaRepository.save(User.fromDomain(userPointDto)).toUserPointDomain();
    }
}
