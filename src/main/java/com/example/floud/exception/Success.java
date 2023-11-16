package com.example.floud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Success {

    //200 OK

    UPDATE_COMMENT_SUCCESS(HttpStatus.OK, "댓글이 성공적으로 수정되었습니다."),
    DELETE_COMMENT_SUCCESS(HttpStatus.OK, "댓글이 성공적으로 삭제되었습니다."),
    DELETE_LIKE_SUCCESS(HttpStatus.OK, "좋아요가 성공적으로 취소되었습니다."),

    //201 CREATED
    SAVE_COMMENT_SUCCESS(HttpStatus.OK, "댓글이 성공적으로 등록되었습니다."),
    SAVE_LIKE_SUCCESS(HttpStatus.OK, "좋아요가 성공적으로 등록되었습니다."),
    SAVE_MEMOIR_SUCCESS(HttpStatus.OK,"회고가 성공적으로 등록되었습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
