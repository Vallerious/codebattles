package com.codebattles.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<CodebattlesUser, String> {
     CodebattlesUser findByEmail(String email);
     CodebattlesUser findByUsername(String username);
}