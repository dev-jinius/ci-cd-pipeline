package com.jinius.ecommerce.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //유저 관련
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST.value(),"ERR-001", "파라미터를 확인해주세요."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST.value(),"ERR-101", "일치하는 유저가 없습니다."),
    NOT_ENOUGH_POINT(HttpStatus.BAD_REQUEST.value(),"ERR-102", "잔액이 부족합니다."),

    //주문 관련
    FAIL_UPDATE_ORDER_STATUS(HttpStatus.BAD_REQUEST.value(),"ERR-201", "주문 상태 업데이트에 실패했습니다."),

    //상품 관련
    NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST.value(),"ERR-301", "상품이 존재하지 않습니다."),
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST.value(),"ERR-302", "주문하신 상품 재고가 부족합니다."),
    ;

    private int status;
    private String code;
    private String message;
}
