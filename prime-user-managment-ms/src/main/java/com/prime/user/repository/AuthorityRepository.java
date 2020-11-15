package com.prime.user.repository;

import com.prime.common.security.model.ERole;
import com.prime.user.model.Authority;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Optional<Authority> findAuthorityByName(ERole name);

}
