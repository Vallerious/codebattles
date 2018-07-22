package com.codebattles.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.codebattles.user.User;

public interface IUserService {
    public List<User> getAllUsers(Sort sort);
    public Page<User> listAllByPage(Pageable pageable);
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public void saveUser(User user);
}
