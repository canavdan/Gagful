package com.gagful.service.impl;

import com.gagful.dto.CommentDTO;
import com.gagful.entity.Comment;
import com.gagful.exception.PostNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.CommentRepository;
import com.gagful.repository.PostRepository;
import com.gagful.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
@Slf4j
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final Mapper mapper;

    @Override
    public List<CommentDTO> getComments(String postId) {
        log.info("Get comments for postId: " + postId);
        postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postID: " + postId));
        List<Comment> comments = commentRepository.findByPost_Id(postId);
        return mapper.mapList(comments, CommentDTO.class);
    }

    @Override
    public Long countByPost_Id(String postId) {
        return commentRepository.countByPost_Id(postId);
    }

}
