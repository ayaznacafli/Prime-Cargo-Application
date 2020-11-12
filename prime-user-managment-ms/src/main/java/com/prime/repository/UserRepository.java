package com.prime.repository;

import com.prime.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @EntityGraph(attributePaths = "authorities")
    @Query("select u from User u where lower(u.username)=lower(:username)")
    Optional<User> findOneWithAuthoritiesByUsername(@Param("username") String username);

    Optional<User> findUserByUsernameIgnoreCase(String username);
    Optional<User> findUserByEmailIgnoreCase(String email);
}
