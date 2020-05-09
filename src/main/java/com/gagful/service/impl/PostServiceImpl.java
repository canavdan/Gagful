package com.gagful.service.impl;

import com.gagful.constant.ExceptionConstant;
import com.gagful.dto.request.PostRequestDTO;
import com.gagful.dto.response.PostResponseDTO;
import com.gagful.entity.Post;
import com.gagful.exception.PostNotFoundException;
import com.gagful.exception.UserNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.PostRepository;
import com.gagful.repository.UserRepository;
import com.gagful.service.PostService;
import com.gagful.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
@Slf4j
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final Mapper mapper;

    @Override
    public List<PostResponseDTO> findAll() {
        log.info("Find all posts.");
        return mapper.mapList(postRepository.findAll(), PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO save(@Valid PostRequestDTO postDTO) {
        log.info("Save new post: " + postDTO.toString());
        Post post = mapper.mapEntity(postDTO, Post.class);
        return mapper.mapEntity(postRepository.save(post), PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO findById(String postId) {
        log.info("Find post by id: " + postId);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postID: " + postId));
        return mapper.mapEntity(post, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findByUsername(String username) {
        log.info("Find post by username: " + username);
        userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(ExceptionConstant.USER_NOT_FOUND + username));
        List<Post> postList = postRepository.findByUser_Username(username);
        return mapper.mapList(postList, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findByTitleContainingIgnoreCase(String searchText) {
        log.info("Find post by search text: " + searchText);
        List<Post> postList = postRepository.findByTitleContainingIgnoreCase(searchText);
        return mapper.mapList(postList, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findByOrderByCreatedDateAsc() {
        log.info("Get order by date posts.");
        return mapper.mapList(postRepository.findByOrderByCreatedDateAsc(), PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findTrendingPost() {
        log.info("Get trending posts.");
        Date minus7Days = DateTimeUtil.minusDays(new Date(), 7);
        List<Post> postList = postRepository.findByCreatedDateGreaterThan(minus7Days);
        return mapper.mapList(postList, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findByCommentsAndUsername(String username) {
        log.info("Get posts by username.");
        userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found with username: " + username));
        List<Post> postList = postRepository.findByComments_User_Username(username);
        return mapper.mapList(postList, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findByVotesAndUsername(String username) {
        log.info("Get posts by votes.");
        userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found with username: " + username));
        List<Post> postList = postRepository.findByVoteUsers_User_Username(username);
        return mapper.mapList(postList, PostResponseDTO.class);
    }
}
