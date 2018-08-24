package com.codebattles.practice.interfaces;

import java.util.List;

import com.codebattles.practice.models.PracticeProblem;
import com.codebattles.practice.models.Solved;
import com.codebattles.user.models.CodebattlesUser;

public interface ISolvedService {
  public Solved recordSolvedProblem(CodebattlesUser user, PracticeProblem practiceProblem);
  
  public boolean checkIfProblemSolved(CodebattlesUser user, PracticeProblem practiceProblem);
  
  public List<Long[]> getDateRating(String userId);
}
