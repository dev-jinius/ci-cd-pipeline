package com.jinius.ecommerce.user.application;


import com.jinius.ecommerce.common.ApiException;
import com.jinius.ecommerce.common.ErrorCode;
import com.jinius.ecommerce.user.domain.model.UserPointDto;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserRequest {
    private Long userId;
    private BigInteger point;

    public static void validate(UserRequest request) {
        if (ObjectUtils.isEmpty(request.getUserId()) || ObjectUtils.isEmpty(request.getPoint()))
            throw new ApiException(ErrorCode.INVALID_PARAMETER);
        if (request.getUserId() <= 0 || request.getPoint().compareTo(BigInteger.ZERO) <= 0)
            throw new ApiException(ErrorCode.INVALID_PARAMETER);
    }

    public UserPointDto toUserPointDto() {
        return UserPointDto.builder()
                .userId(userId)
                .point(point)
                .build();
    }
}
