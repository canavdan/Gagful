package com.gagful.service.impl;

import com.gagful.dto.CommentDTO;
import com.gagful.entity.Comment;
import com.gagful.mapper.Mapper;
import com.gagful.repository.CommentRepository;
import com.gagful.service.CommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Validated
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<CommentDTO> getComments(String postId) {
        List<Comment> comments=commentRepository.findByPost_Id(postId);
        return mapper.mapList(comments,CommentDTO.class);
    }

}
