package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.dto.CommentDTO;
import com.gagful.dto.PostDTO;
import com.gagful.entity.Comment;
import com.gagful.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(APIConstants.VERSION + "/comment")
public class CommentController  extends BaseController<Comment> {

    @Autowired
    private CommentService commentService;

    @GetMapping(value ="/{postId}")
    public ResponseEntity<BaseResponse> findByPostId(@PathVariable String postId){
        List<CommentDTO> commentDTOList=commentService.getComments(postId);
        return responseUtil.successListResponse(commentDTOList,1,10);
    }
}
