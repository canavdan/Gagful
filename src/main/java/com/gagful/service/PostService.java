package com.gagful.service;

import com.gagful.dto.UserDTO;
import com.gagful.dto.request.PostRequestDTO;
import com.gagful.dto.response.PostResponseDTO;
import com.gagful.entity.User;

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

    PostResponseDTO downVote(String postId,UserDTO user);

    PostResponseDTO upVote(String postId, UserDTO user);

    List<PostResponseDTO> findByCategory(String categoryName);
}
