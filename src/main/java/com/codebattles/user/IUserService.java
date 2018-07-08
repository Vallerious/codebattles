package com.codebattles.user;

import com.codebattles.user.User;

public interface IUserService {
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public void saveUser(User user);
}
