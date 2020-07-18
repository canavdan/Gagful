package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseRequest;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.constant.VoteType;
import com.gagful.dto.UserDTO;
import com.gagful.dto.request.PostRequestDTO;
import com.gagful.dto.response.PostResponseDTO;
import com.gagful.entity.Post;
import com.gagful.entity.User;
import com.gagful.service.PostService;
import com.gagful.service.UserService;
import com.gagful.util.WebContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(APIConstants.VERSION)
public class PostController extends BaseController<Post> {


    private final PostService postService;

    private final UserService userService;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping(value = "/admin/secure/posts", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> findAll(HttpServletRequest request) {
        List<PostResponseDTO> postList = postService.findAll();
        return responseUtil.successListResponse(postList);
    }

    @PostMapping(value = "/admin/secure/save", produces = APIConstants.CHARSET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse> save(@RequestPart MultipartFile image, @RequestPart BaseRequest<PostRequestDTO> postRequestDTO) throws IOException {
        postRequestDTO.getData().setImage(image.getBytes());
        PostResponseDTO postDTOSaved = postService.save(postRequestDTO.getData());
        return responseUtil.successResponse(postDTOSaved);
    }

    @GetMapping(value = "/admin/secure/post/{postId}")
    public ResponseEntity<BaseResponse> findById(@PathVariable String postId) {
        PostResponseDTO postDTO = postService.findById(postId);
        return responseUtil.successResponse(postDTO);
    }

    @GetMapping(value = "/admin/secure/post")
    public ResponseEntity<BaseResponse> findByUsername(@RequestParam(value = "username") String username) {
        List<PostResponseDTO> postDTOList = postService.findByUsername(username);
        return responseUtil.successListResponse(postDTOList);
    }

    @GetMapping(value = "/public/posts/search")
    public ResponseEntity<BaseResponse> findBySearchText(@RequestParam(value = "searchText") String searchText) {
        List<PostResponseDTO> postDTOList = postService.findByTitleContainingIgnoreCase(searchText);
        return responseUtil.successListResponse(postDTOList);
    }

    @GetMapping(value = "/public/posts/popular")
    public ResponseEntity<BaseResponse> findPopularPosts() {
        List<PostResponseDTO> postDTOList = postService.findAll();
        Comparator<PostResponseDTO> compareByVoteCount = (PostResponseDTO o1, PostResponseDTO o2) -> o1.getVotes().stream().
                filter(a -> a.getType() == VoteType.UPVOTE).
                findFirst().get().getCount().compareTo(o2.getVotes().stream().
                filter(a -> a.getType() == VoteType.UPVOTE).
                findFirst().get().getCount());
        Collections.sort(postDTOList, compareByVoteCount.reversed());
        return responseUtil.successListResponse(postDTOList);
    }

    @GetMapping(value = "/public/posts/fresh")
    public ResponseEntity<BaseResponse> findNewPosts() {
        List<PostResponseDTO> postDTOList = postService.findByOrderByCreatedDateAsc();
        return responseUtil.successListResponse(postDTOList);
    }

    @GetMapping(value = "/public/posts/trending")
    public ResponseEntity<BaseResponse> findTrendingPosts() {
        List<PostResponseDTO> postDTOList = postService.findTrendingPost();
        return responseUtil.successListResponse(postDTOList);
    }

    @GetMapping(value = "/admin/secure/profile/comments")
    public ResponseEntity<BaseResponse> findByCommentsAndUsername(@RequestParam(value = "username") String username) {
        List<PostResponseDTO> postDTOList = postService.findByCommentsAndUsername(username);
        return responseUtil.successListResponse(postDTOList);
    }

    @GetMapping(value = "/admin/secure/profile/votes")
    public ResponseEntity<BaseResponse> findByVotesAndUsername(@RequestParam(value = "username") String username) {
        List<PostResponseDTO> postDTOList = postService.findByVotesAndUsername(username);
        return responseUtil.successListResponse(postDTOList);
    }

    @GetMapping(value = "/admin/secure/post/down/{postId}")
    public ResponseEntity<BaseResponse> downVote(HttpServletRequest request,@PathVariable String postId) {
        UserDTO user=userService.findByUsername(WebContext.getUser(request));
        //TODO 2 vote for user invalid
        PostResponseDTO postDTO = postService.downVote(postId,user);
        return responseUtil.successResponse(postDTO);
    }

    @GetMapping(value = "/admin/secure/post/up/{postId}")
    public ResponseEntity<BaseResponse> upVote(HttpServletRequest request,@PathVariable String postId) {
        UserDTO user=userService.findByUsername(WebContext.getUser(request));
        //TODO 2 vote for user invalid
        PostResponseDTO postDTO = postService.upVote(postId,user);
        return responseUtil.successResponse(postDTO);
    }

}