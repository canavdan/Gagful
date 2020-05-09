package com.gagful.service;

import com.gagful.dto.request.PostRequestDTO;
import com.gagful.dto.response.PostResponseDTO;

import javax.validation.Valid;
import java.util.List;

public interface PostService {
    List<PostResponseDTO> findAll();

    PostResponseDTO save(@Valid PostRequestDTO postDTO);

    PostResponseDTO findById(String postId);

    List<PostResponseDTO> findByUsername(String username);

    List<PostResponseDTO> findByTitleContainingIgnoreCase(String searchText);

    List<PostResponseDTO> findByOrderByCreatedDateAsc();

    List<PostResponseDTO> findTrendingPost();

    List<PostResponseDTO> findByCommentsAndUsername(String username);

    List<PostResponseDTO> findByVotesAndUsername(String username);
}
