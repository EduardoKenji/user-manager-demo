package com.user.usermanagerdemo.repository;

import com.user.usermanagerdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA methods are transactional, so @Transaction isn't needed here or on service layer (and its methods)
public interface UserRepository extends JpaRepository<User, Long> { // JpaRepository<entity class, id type>
}
