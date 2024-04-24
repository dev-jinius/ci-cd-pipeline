package com.jinius.ecommerce.user.domain.model;

import com.jinius.ecommerce.cart.domain.model.CartDto;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserDto {

    private Long userId;
    private BigInteger point;
    private List<CartDto> carts;
}
