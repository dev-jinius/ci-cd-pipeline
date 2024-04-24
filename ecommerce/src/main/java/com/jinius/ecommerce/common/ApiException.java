package com.jinius.ecommerce.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException {

    private ErrorCode errorCode;
}
