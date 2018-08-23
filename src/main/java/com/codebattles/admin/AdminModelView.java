package com.codebattles.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.codebattles.practice.PracticeProblem;
import com.codebattles.role.Role;
import com.codebattles.user.CodebattlesUser;

public class AdminModelView {
  private List<CodebattlesUser> users;
  
  private List<PracticeProblem> problems;
  
  private List<List<String>> userRoles;

  public AdminModelView(List<CodebattlesUser> users, List<PracticeProblem> problems) {
    super();
    setUsers(users);
    this.problems = problems;
    this.userRoles = this.users.stream()
        .map(u -> mapRolesToString(u))
        .collect(Collectors.toList());
  }
  
  private List<String> mapRolesToString(CodebattlesUser user) {
    Set<Role> roles = user.getRoles();
    List<Role> rolesList = new ArrayList<>(roles);

    return rolesList.stream().map(role -> role.getRole()).collect(Collectors.toList());
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

}
