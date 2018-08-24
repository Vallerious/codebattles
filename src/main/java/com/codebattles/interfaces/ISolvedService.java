package com.codebattles.interfaces;

import java.util.List;

import com.codebattles.models.CodebattlesUser;
import com.codebattles.models.PracticeProblem;
import com.codebattles.models.Solved;

public interface ISolvedService {
  public Solved recordSolvedProblem(CodebattlesUser user, PracticeProblem practiceProblem);
  
  public boolean checkIfProblemSolved(CodebattlesUser user, PracticeProblem practiceProblem);
  
  public List<Long[]> getDateRating(String userId);
}
