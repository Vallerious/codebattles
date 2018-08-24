package com.codebattles.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.codebattles.models.CodebattlesUser;

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<CodebattlesUser, String> {
     CodebattlesUser findByEmail(String email);
     
     CodebattlesUser findByUsername(String username);
}