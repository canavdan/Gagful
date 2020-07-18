package com.gagful.repository;

import com.gagful.entity.User;
import com.gagful.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, String> {
}
