package com.ssafy.userservice.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.userservice.db.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserSnsId(String userSnsId);
	Optional<User> findByUserNickname(String userNickname);
}
