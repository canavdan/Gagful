package com.gagful.service.impl;

import com.gagful.constant.ExceptionConstant;
import com.gagful.constant.VoteType;
import com.gagful.dto.UserDTO;
import com.gagful.dto.request.PostRequestDTO;
import com.gagful.dto.response.PostResponseDTO;
import com.gagful.entity.Post;
import com.gagful.entity.User;
import com.gagful.entity.Vote;
import com.gagful.entity.VoteUser;
import com.gagful.exception.PostNotFoundException;
import com.gagful.exception.UserNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.PostRepository;
import com.gagful.repository.UserRepository;
import com.gagful.repository.VoteRepository;
import com.gagful.repository.VoteUserRepository;
import com.gagful.service.PostService;
import com.gagful.service.RedisService;
import com.gagful.service.UserService;
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

    private final VoteRepository voteRepository;

    private final VoteUserRepository voteUserRepository;

    private final RedisService redisService;

    private final UserService userService;

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
        PostResponseDTO postResponseDTO=mapper.mapEntity(postRepository.save(post), PostResponseDTO.class);
        Vote upVote= Vote.builder().post(post).count(Long.valueOf(0)).type(VoteType.UPVOTE).build();
        Vote downVote=Vote.builder().post(post).count(Long.valueOf(0)).type(VoteType.DOWNVOTE).build();
        voteRepository.save(upVote);
        voteRepository.save(downVote);
        return postResponseDTO;
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

    @Override
    public PostResponseDTO upVote(String postId,UserDTO user) {
        log.info("Up vote for post id: " + postId);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postID: " + postId));
        Vote postUpVote = post.getVotes().stream().filter(vote -> vote.getType()==VoteType.UPVOTE).findFirst().get();
        postUpVote.setCount(postUpVote.getCount() + 1);
        addVoteForUser(post,user.getId(),VoteType.UPVOTE);
        return findById(postId);
    }

    @Override
    public PostResponseDTO downVote(String postId,UserDTO user) {
        log.info("Down vote for post id: " + postId);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found with postID: " + postId));
        Vote postUpVote = post.getVotes().stream().filter(vote -> vote.getType()==VoteType.DOWNVOTE).findFirst().get();
        postUpVote.setCount(postUpVote.getCount() + 1);
        addVoteForUser(post,user.getId(),VoteType.DOWNVOTE);
        return findById(postId);
    }

    public void addVoteForUser(Post post,String userId,VoteType voteType) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with userId: " + userId));
        VoteUser voteUser=VoteUser.builder().post(post).user(user).type(voteType).build();
        voteUserRepository.save(voteUser);
    }

}
