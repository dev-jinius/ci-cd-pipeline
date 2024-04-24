package com.jinius.ecommerce.order.domain.model;

import com.jinius.ecommerce.user.domain.model.UserPointDto;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderDto {

    private Long orderId;                   //주문 ID
    private UserPointDto user;              //유저 ID
    private OrderStatusType orderStatus;    //주문 상태 ["CANCEL", "DONE"]
    private BigInteger orderPrice;          //총 주문 금액
    private List<OrderItemDto> orderItems;  //주문 상품 목록

    public void setOrderStatus(OrderStatusType orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 주문 생성
     * @param user  유저
     * @param orderStatus "DONE"
     * @param orderItems 요청한 주문 상품 목록
     * @return OrderDto
     */
    public static OrderDto makeOrder(UserPointDto user, OrderStatusType orderStatus, List<OrderItemDto> orderItems) {
        return OrderDto.builder()
                .user(user)
                .orderStatus(orderStatus)
                .orderItems(orderItems)
                .build();
    }

    /**
     * 주문 총 금액 계산
     */
    public BigInteger calculateOrderPrice() {
        BigInteger totalPrice = BigInteger.ZERO;
        for (OrderItemDto orderItem : this.orderItems) {
            totalPrice = totalPrice.add(orderItem.getItemPrice());
        }
        this.orderPrice = totalPrice;
        return totalPrice;
    }
}
