package com.prime.repository;

import com.prime.common.security.model.ERole;
import com.prime.model.Authority;
import com.prime.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Optional<Authority> findAuthorityByName(ERole name);

}
