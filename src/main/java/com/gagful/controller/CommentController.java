package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.dto.CommentDTO;
import com.gagful.entity.Comment;
import com.gagful.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(APIConstants.VERSION)
public class CommentController extends BaseController<Comment> {


    private final CommentService commentService;

    @GetMapping(value = "/public/comments/{postId}")
    public ResponseEntity<BaseResponse> findByPostId(@PathVariable String postId) {
        List<CommentDTO> commentDTOList = commentService.getComments(postId);
        return responseUtil.successListResponse(commentDTOList, 1, 10);
    }
}
