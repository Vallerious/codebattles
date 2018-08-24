package com.codebattles.modelviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.codebattles.models.CodebattlesUser;
import com.codebattles.models.PracticeProblem;
import com.codebattles.models.Role;

public class AdminViewModel {
  private List<CodebattlesUser> users;
  
  private List<PracticeProblem> problems;
  
  private List<List<String>> userRoles;
  
  private String currentUserId;

  public AdminViewModel(
      List<CodebattlesUser> users,
      List<PracticeProblem> problems,
      String currentUserId,
      List<List<String>> userRoles
      ) {
    super();
    setUsers(users);
    this.problems = problems;
    this.userRoles = userRoles;
    this.currentUserId = currentUserId;
  }

  public void setUsers(List<CodebattlesUser> users) {
    this.users = users;
  }

  public List<CodebattlesUser> getUsers() {
    return users;
  }

  public List<PracticeProblem> getProblems() {
    return problems;
  }

  public List<List<String>> getUserRoles() {
    return userRoles;
  }

  public String getCurrentUserId() {
    return currentUserId;
  }

  public void setCurrentUserId(String currentUserId) {
    this.currentUserId = currentUserId;
  }
  
  

}
