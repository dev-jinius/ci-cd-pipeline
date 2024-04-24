package com.jinius.ecommerce.item.domain.model;

import lombok.*;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ItemDto {

    private Long itemId;
    private String itemName;
    private BigInteger itemPrice;
    private Long stockQuantity;
}
