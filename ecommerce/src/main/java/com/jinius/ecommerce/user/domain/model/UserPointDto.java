package com.jinius.ecommerce.user.domain.model;

import lombok.*;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserPointDto {

    private Long userId;
    private BigInteger point;

    //포인트 충전
    public void addPoint(BigInteger point) {
        this.point = this.point.add(point);
    }

    //포인트 차감
    public void subtractPoint(BigInteger point) {this.point = this.point.subtract(point);}
}
