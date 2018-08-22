package com.codebattles.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codebattles.practice.IPracticeProblemService;
import com.codebattles.practice.PracticeProblem;
import com.codebattles.user.CodebattlesUser;
import com.codebattles.user.IUserService;

public class AdminModelView {
  private List<CodebattlesUser> users;
  
  private List<PracticeProblem> problems;

  public AdminModelView(List<CodebattlesUser> users, List<PracticeProblem> problems) {
    super();
    this.users = users;
    this.problems = problems;
  }

  public List<CodebattlesUser> getUsers() {
    return users;
  }

  public List<PracticeProblem> getProblems() {
    return problems;
  }

}
