package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseRequest;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.constant.VoteType;
import com.gagful.dto.PostDTO;
import com.gagful.entity.Post;
import com.gagful.service.PostService;
import com.gagful.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(APIConstants.VERSION + "/post")
public class PostController extends BaseController<Post> {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> findAll(HttpServletRequest request) {
        List<PostDTO> postList = postService.findAll();
        return responseUtil.successListResponse(postList, 1, 10);
    }

    @PostMapping(value = "/save/", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> save(HttpServletRequest request, @RequestBody BaseRequest<PostDTO> postDTO) {
        PostDTO postDTOSaved = postService.save(postDTO.getData());
        return responseUtil.successResponse(postDTOSaved);
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<BaseResponse> findById(@PathVariable String postId) {
        PostDTO postDTO = postService.findById(postId);
        return responseUtil.successResponse(postDTO);
    }

    @GetMapping(value = "/")
    public ResponseEntity<BaseResponse> findByUsername(@RequestParam(value = "username") String username) {
        List<PostDTO> postDTOList = postService.findByUsername(username);
        return responseUtil.successListResponse(postDTOList, 1, 10);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<BaseResponse> findBySearchText(@RequestParam(value = "searchText") String searchText) {
        List<PostDTO> postDTOList = postService.findByTitleContainingIgnoreCase(searchText);
        return responseUtil.successListResponse(postDTOList, 1, 10);
    }

    @GetMapping(value = "/popular")
    public ResponseEntity<BaseResponse> findPopularPosts() {
        List<PostDTO> postDTOList = postService.findAll();
        Comparator<PostDTO> compareByVoteCount = (PostDTO o1, PostDTO o2) -> o1.getVotes().stream().
                filter(a -> a.getType() == VoteType.UPVOTE).
                findFirst().get().getCount().compareTo(o2.getVotes().stream().
                filter(a -> a.getType() == VoteType.UPVOTE).
                findFirst().get().getCount());
        Collections.sort(postDTOList, compareByVoteCount.reversed());
        return responseUtil.successListResponse(postDTOList, 1, 10);
    }

    @GetMapping(value = "/fresh")
    public ResponseEntity<BaseResponse> findNewPosts() {
        List<PostDTO> postDTOList = postService.findByOrderByCreatedDateAsc();
        return responseUtil.successListResponse(postDTOList, 1, 10);
    }
    @GetMapping(value = "/trending")
    public ResponseEntity<BaseResponse> findTrendingPosts() {
        List<PostDTO> postDTOList = postService.findTrendingPost();
        return responseUtil.successListResponse(postDTOList, 1, 10);
    }
}