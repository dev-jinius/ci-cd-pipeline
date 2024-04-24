package com.jinius.ecommerce.user.infra;

import com.jinius.ecommerce.cart.infra.Cart;
import com.jinius.ecommerce.user.domain.model.UserDto;
import com.jinius.ecommerce.user.domain.model.UserPointDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private BigInteger point;

    /**
     * 장바구니 정보
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> carts;

    public static User fromDomain(UserDto userDto) {
        return User.builder()
                .id(userDto.getUserId())
                .point(userDto.getPoint())
                .carts(userDto.getCarts().stream().map(cart -> Cart.fromDomain(cart)).toList())
                .build();
    }

    public static User fromDomain(UserPointDto userPointDto) {
        return User.builder()
                .id(userPointDto.getUserId())
                .point(userPointDto.getPoint())
                .build();
    }

    public UserDto toUserDomain() {
        return UserDto.builder()
                .userId(id)
                .point(point)
                .carts(carts.stream().map(cart -> cart.toDomain()).toList())
                .build();
    }
    public UserPointDto toUserPointDomain() {
        return UserPointDto.builder()
                .userId(id)
                .point(point)
                .build();
    }

}
