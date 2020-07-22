package com.gagful.service;

import com.gagful.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getComments(String postId);

    Long countByPost_Id(String postId);
}
