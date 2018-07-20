package com.codebattles.user;

import java.util.List;

import com.codebattles.user.User;

public interface IUserService {
    public List<User> getAllUsers();
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public void saveUser(User user);
}
