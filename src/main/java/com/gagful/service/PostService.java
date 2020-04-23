package com.gagful.service;

import com.gagful.dto.PostDTO;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface PostService {
    List<PostDTO> findAll();
    PostDTO save(@Valid PostDTO postDTO);
    PostDTO findById(String postId);
    List<PostDTO> findByUsername(String username);
    List<PostDTO> findByTitleContainingIgnoreCase(String searchText);
    List<PostDTO> findByOrderByCreatedDateAsc();
    List<PostDTO> findTrendingPost();
}
