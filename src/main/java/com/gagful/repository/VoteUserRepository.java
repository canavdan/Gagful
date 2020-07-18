package com.gagful.repository;
import com.gagful.entity.VoteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteUserRepository extends JpaRepository<VoteUser, String> {
}
