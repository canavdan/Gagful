package com.gagful.repository;

import com.gagful.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findByUser_Username(String username);

    Optional<Post> findById(String id);

    List<Post> findByTitleContainingIgnoreCase(String searchText);

    List<Post> findByOrderByCreatedDateAsc();

    List<Post> findByCreatedDateGreaterThan(Date date);

    List<Post> findByComments_User_Username(String username);

    List<Post> findByVoteUsers_User_Username(String username);
}
