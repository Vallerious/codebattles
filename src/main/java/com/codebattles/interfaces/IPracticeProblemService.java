package com.codebattles.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.codebattles.models.PracticeProblem;
import com.codebattles.models.Problem;

public interface IPracticeProblemService {
  public List<PracticeProblem> getProblems();
  
  public PracticeProblem getProblem(String id);
  
  public String[] checkProblem(String problemCode, String problemId, String problemName, int testCase);
  
  public String[] checkProblems(String problemCode, String problemId, String problemName);
  
  public void incrementSolvedCount(String problemId);
  
  public void addProblem(Problem problem) throws IOException;
  
  public Set<String> findSolvedProblems(String userId);
  
  public void saveProblemToDB(Problem problem);
}
