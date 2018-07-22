package com.codebattles.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, String> {
     User findByEmail(String email);
     User findByUsername(String username);
}