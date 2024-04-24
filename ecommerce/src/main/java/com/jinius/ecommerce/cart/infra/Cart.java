package com.jinius.ecommerce.cart.infra;

import com.jinius.ecommerce.cart.domain.model.CartDto;
import com.jinius.ecommerce.item.infra.Item;
import com.jinius.ecommerce.user.infra.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cart")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Long quantity;

    @CreatedDate
    private LocalDateTime createDate;

    public static Cart fromDomain(CartDto cartDto) {
        return Cart.builder()
                .id(cartDto.getCartId())
                .user(User.fromDomain(cartDto.getUser()))
                .item(Item.fromDomain(cartDto.getItem()))
                .quantity(cartDto.getQuantity())
                .build();
    }

    public CartDto toDomain() {
        return CartDto.builder()
                .cartId(id)
                .user(user.toUserDomain())
                .item(item.toDomain())
                .quantity(quantity)
                .createDate(createDate)
                .build();
    }
}
