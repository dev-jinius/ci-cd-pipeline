package com.jinius.ecommerce.user.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jinius.ecommerce.cart.domain.model.CartDto;
import com.jinius.ecommerce.user.domain.model.UserDto;
import com.jinius.ecommerce.user.domain.model.UserPointDto;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserResponse {

    private Long userId;                //유저 ID
    private BigInteger point;           //잔액 포인트
    @JsonIgnore
    private List<CartDto> carts;        //장바구니
    

    public static UserResponse of(UserDto dto) {
        return UserResponse.builder()
                .userId(dto.getUserId())
                .point(dto.getPoint())
                .carts(dto.getCarts())
                .build();
    }

    public static UserResponse of(UserPointDto dto) {
        return UserResponse.builder()
                .userId(dto.getUserId())
                .point(dto.getPoint())
                .build();
    }

    public UserPointDto toUserPointDto() {
        return UserPointDto.builder()
                .userId(userId)
                .point(point)
                .build();
    }
}
