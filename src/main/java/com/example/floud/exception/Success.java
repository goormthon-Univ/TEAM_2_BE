package com.example.floud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Success {

    //200 OK
    GET_USER_SUCCESS(HttpStatus.OK, "로그인이 성공적으로 진행되었습니다."),
    GET_MAIN_SUCCESS(HttpStatus.OK, "메인 화면 정보를 성공적으로 조회했습니다."),
    GET_ONE_MEMOIR_SUCCESS(HttpStatus.OK, "회고를 성공적으로 조회했습니다."),
    GET_ANONYMOUS_MEMOIR_SUCCESS(HttpStatus.OK, "익명 회고를 성공적으로 조회했습니다."),
    UPDATE_MEMOIR_SUCCESS(HttpStatus.OK, "회고가 성공적으로 수정되었습니다."),
    DELETE_MEMOIR_SUCCESS(HttpStatus.NO_CONTENT, "회고가 성공적으로 삭제되었습니다."),
    UPDATE_COMMENT_SUCCESS(HttpStatus.OK, "댓글이 성공적으로 수정되었습니다."),
    DELETE_COMMENT_SUCCESS(HttpStatus.NO_CONTENT, "댓글이 성공적으로 삭제되었습니다."),
    GET_MY_COMMENT_SUCCESS(HttpStatus.NO_CONTENT, "내가 단 댓글 목록을 성공적으로 조회하였습니다."),
    DELETE_LIKE_SUCCESS(HttpStatus.NO_CONTENT, "좋아요가 성공적으로 취소되었습니다."),
    GET_MY_LIKE_MEMOIR_SUCCESS(HttpStatus.NO_CONTENT, "내가 좋아요 누른 회고록을 성공적으로 조회하였습니다."),
    GET_MY_ALARM_SUCCESS(HttpStatus.NO_CONTENT, "나의 알람 목록을 성공적으로 조회하였습니다."),



    //201 CREATED
    SAVE_USER_SUCCESS(HttpStatus.OK, "회원이 성공적으로 등록되었습니다."),
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
