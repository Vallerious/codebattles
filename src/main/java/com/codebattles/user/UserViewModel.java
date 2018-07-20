package com.codebattles.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserViewModel {

  @Autowired
  private UserService userService;
  
  public List<User> getAllUsersForTable() {
    return this.userService.getAllUsers();
  }
}
