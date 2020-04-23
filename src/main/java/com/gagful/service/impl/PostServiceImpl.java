package com.gagful.service.impl;

import com.gagful.dto.PostDTO;
import com.gagful.entity.Post;
import com.gagful.exception.PostNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.PostRepository;
import com.gagful.service.PostService;
import com.gagful.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<PostDTO> findAll() {
        return mapper.mapList(postRepository.findAll(),PostDTO.class);
    }

    @Override
    public PostDTO save(@Valid PostDTO postDTO) {
        Post post = mapper.mapEntity(postDTO,Post.class);
        return mapper.mapEntity(postRepository.save(post),PostDTO.class);
    }

    @Override
    public PostDTO findById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postID: " + postId));
        return mapper.mapEntity(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> findByUsername(String username) {
        List<Post> postList = postRepository.findByUser_Username(username);
        return mapper.mapList(postList,PostDTO.class);
    }

    @Override
    public List<PostDTO> findByTitleContainingIgnoreCase(String searchText) {
        List<Post> postList=postRepository.findByTitleContainingIgnoreCase(searchText);
        return mapper.mapList(postList,PostDTO.class);
    }

    @Override
    public List<PostDTO> findByOrderByCreatedDateAsc(){
        return mapper.mapList(postRepository.findByOrderByCreatedDateAsc(),PostDTO.class);
    }

    @Override
    public List<PostDTO> findTrendingPost() {
        Date minus7Days=DateTimeUtil.minusDays(new Date(),7);
        List<Post> postList = postRepository.findByCreatedDateGreaterThan(minus7Days);
        return mapper.mapList(postList,PostDTO.class);
    }

}
