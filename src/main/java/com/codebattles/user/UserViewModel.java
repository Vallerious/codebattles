package com.codebattles.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserViewModel {

  @Autowired
  private UserService userService;
  
  public Page<User> getAllUsersForTable(Pageable pageable) {
    return this.userService.listAllByPage(pageable);
  }
}
