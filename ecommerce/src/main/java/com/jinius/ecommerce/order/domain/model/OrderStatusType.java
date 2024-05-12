package com.jinius.ecommerce.order.domain.model;

public enum OrderStatusType {
    /**
     * 주문 상태
     */
    CANCEL, // 주문 취소
    DONE,   // 주문 완료

    /**
     * 주문 상품 상태
     */
    ORDER,  // 상품 주문
    REFUND  // 상품 환불
}