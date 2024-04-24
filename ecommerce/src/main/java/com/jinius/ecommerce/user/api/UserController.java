package com.jinius.ecommerce.user.api;

import com.jinius.ecommerce.user.application.UserPointFacade;
import com.jinius.ecommerce.user.application.UserRequest;
import com.jinius.ecommerce.user.application.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce/user")
@Tag(name = "User", description = "유저 API")
@RequiredArgsConstructor
public class UserController {

    private final UserPointFacade userPointFacade;

    /**
     * 포인트 조회
     * @param userId
     * @return
     */
    @GetMapping("/point/{userId}")
    @Operation(summary = "유저 포인트 조회 API", description = "유저 포인트 조회")
    @Schema(description = "유저 포인트 조회 응답")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "유저 없음", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(type = "string", description = "에러 코드", defaultValue = "ERR-101")),
                    @SchemaProperty(name = "message", schema = @Schema(type = "string", description = "에러 메시지", defaultValue = "일치하는 유저가 없습니다."))
            })),
            @ApiResponse(responseCode = "400", description = "파라미터 없음", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(type = "string", description = "에러 코드", defaultValue = "ERR-001")),
                    @SchemaProperty(name = "message", schema = @Schema(type = "string", description = "에러 메시지", defaultValue = "파라미터를 확인해주세요."))
            })),
    })
    public ResponseEntity<UserResponse> userPoint(@PathVariable(value = "userId") Long userId) {

        // 조회한 유저 포인트를 반환한다.
        UserResponse result = userPointFacade.userPoint(userId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * 포인트 충전
     */
    @PatchMapping("/point/charge")
    @Operation(summary = "유저 포인트 충전 API", description = "유저 포인트 충전")
    @Schema(description = "유저 포인트 충전 응답")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "유저 없음", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(type = "string", description = "에러 코드", defaultValue = "ERR-101")),
                    @SchemaProperty(name = "message", schema = @Schema(type = "string", description = "에러 메시지", defaultValue = "일치하는 유저가 없습니다."))
            })),
            @ApiResponse(responseCode = "400", description = "파라미터 없음", content = @Content(schemaProperties = {
                    @SchemaProperty(name = "code", schema = @Schema(type = "string", description = "에러 코드", defaultValue = "ERR-001")),
                    @SchemaProperty(name = "message", schema = @Schema(type = "string", description = "에러 메시지", defaultValue = "파라미터를 확인해주세요."))
            })),
    })
    public ResponseEntity<UserResponse> charge(@RequestBody UserRequest request) {

        //충전 후 유저 잔액 포인트를 반환한다.
        UserResponse result = userPointFacade.chargePoint(request);

        return ResponseEntity.ok().body(result);
    }
}
