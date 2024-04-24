package com.jinius.ecommerce.order.domain.model;

import com.jinius.ecommerce.item.domain.model.ItemDto;
import lombok.*;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderItemDto {

    private Long orderItemId;
    private OrderDto order;
    private ItemDto item;
    private BigInteger itemPrice;
    private Long orderQuantity;
    private OrderStatusType status;

    public static OrderItemDto makeOrderItem(ItemDto item, BigInteger itemPrice, Long orderQuantity, OrderStatusType status) {
        return OrderItemDto.builder()
                .item(item)
                .itemPrice(itemPrice)
                .orderQuantity(orderQuantity)
                .status(status)
                .build();
    }
}
