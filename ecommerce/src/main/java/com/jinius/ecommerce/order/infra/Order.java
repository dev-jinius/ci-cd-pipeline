package com.jinius.ecommerce.order.infra;

import com.jinius.ecommerce.order.domain.model.OrderDto;
import com.jinius.ecommerce.order.domain.model.OrderStatusType;
import com.jinius.ecommerce.user.infra.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class Order {

    /**
     * 주문 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    /**
     * 유저 정보
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 주문 상태 [CANCLE, DONE]
     */
    private String orderStatus;

    /**
     * 총 주문 가격
     */
    private BigInteger orderPrice;

    /**
     * 주문 시간
     */
    @CreatedDate
    private LocalDateTime createDate;

    /**
     * 주문 상품 정보
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    public static Order fromDomain(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getOrderId())
                .user(User.fromDomain(orderDto.getUser()))
                .orderStatus(String.valueOf(orderDto.getOrderStatus()))
                .orderPrice(orderDto.getOrderPrice())
                .orderItems(orderDto.getOrderItems().stream().map(oi -> OrderItem.fromDomain(oi)).toList())
                .build();
    }

    public OrderDto toDomain() {
        return OrderDto.builder()
                .orderId(id)
                .user(user.toUserPointDomain())
                .orderStatus(OrderStatusType.valueOf(orderStatus))
                .orderPrice(orderPrice)
                .orderItems(orderItems.stream().map(oi -> oi.toDomain()).toList())
                .build();
    }
}