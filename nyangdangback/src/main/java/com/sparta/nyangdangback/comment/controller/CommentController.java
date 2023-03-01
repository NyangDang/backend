package com.sparta.nyangdangback.comment.controller;

import com.sparta.nyangdangback.comment.dto.CommentRequestDto;
import com.sparta.nyangdangback.comment.dto.CommentResponseDto;
import com.sparta.nyangdangback.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController // Json형태로 Body에 가져옴
@RequiredArgsConstructor // final로 선언된 멤버변수 자동생성(DI 의존성 주입)
@RequestMapping("/api/blogs/{id}")
public class CommentController {
    // Service단 연결
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        // List 형태의 CommentResponseDto가 반환타입
        // @PathVariable : URL에서 id값 파라미터로 받아와서 댓글 식별
        // @RequestBody : Client입력값 (comment) -> Json형태로 Body에 CommentRequestDto의 객체에 담아옴
        return commentService.createComment(id, commentRequestDto, request);
        // Service단에 createComment메서드 실행
    }

    // 댓글 수정
//    @PutMapping("/comment")
//    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
//        return commentService.updateComment(id, commentRequestDto.getComment(), request);
//    }

    // 댓글 삭제
//    @DeleteMapping("/comment")
//    public ResponseEntity<StatusMsgResponseDto> deleteComment(@PathVariable Long id, HttpServletRequest request) {
//        return commentService.deleteComment(id, request);
//    }
}
