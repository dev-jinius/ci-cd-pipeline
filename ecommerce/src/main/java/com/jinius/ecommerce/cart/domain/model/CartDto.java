package com.jinius.ecommerce.cart.domain.model;

import com.jinius.ecommerce.item.domain.model.ItemDto;
import com.jinius.ecommerce.user.domain.model.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CartDto {

    private Long cartId;
    private UserDto user;
    private ItemDto item;
    private Long quantity;
    private LocalDateTime createDate;
}