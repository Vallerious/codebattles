package com.codebattles.practice;

import com.codebattles.user.CodebattlesUser;

public interface ISolvedService {
  public Solved recordSolvedProblem(CodebattlesUser user, PracticeProblem practiceProblem);
  
  public boolean checkIfProblemSolved(CodebattlesUser user, PracticeProblem practiceProblem);
}
