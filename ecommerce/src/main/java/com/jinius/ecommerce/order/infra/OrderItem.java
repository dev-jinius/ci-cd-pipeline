package com.jinius.ecommerce.order.infra;

import com.jinius.ecommerce.item.infra.Item;
import com.jinius.ecommerce.order.domain.model.OrderItemDto;
import com.jinius.ecommerce.order.domain.model.OrderStatusType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name = "tb_order_item")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class OrderItem {
    /**
     * 주문 상품 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    /**
     * 주문 정보
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    /**
     * 상품 정보
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /**
     * 상품 가격
     */
    @Column(nullable = false)
    private BigInteger itemPrice;

    /**
     * 주문 수량
     */
    @Column(nullable = false)
    private Long orderQuantity;

    /**
     * 상태
     */
    @Column(nullable = false)
    private String status;

    public static OrderItem fromDomain(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .id(orderItemDto.getOrderItemId())
                .order(Order.fromDomain(orderItemDto.getOrder()))
                .item(Item.fromDomain(orderItemDto.getItem()))
                .itemPrice(orderItemDto.getItemPrice())
                .orderQuantity(orderItemDto.getOrderQuantity())
                .status(String.valueOf(orderItemDto.getStatus()))
                .build();
    }

    public OrderItemDto toDomain() {
        return OrderItemDto.builder()
                .orderItemId(id)
                .order(order.toDomain())
                .item(item.toDomain())
                .itemPrice(itemPrice)
                .orderQuantity(orderQuantity)
                .status(OrderStatusType.valueOf(status))
                .build();
    }
}
