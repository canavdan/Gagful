package com.gagful.repository;

import com.gagful.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPost_Id(String postId);

    Long countByPost_Id(String postId);
}
