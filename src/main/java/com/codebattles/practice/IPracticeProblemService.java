package com.codebattles.practice;

import java.util.List;

public interface IPracticeProblemService {
  public List<PracticeProblem> getProblems();
  
  public PracticeProblem getProblem(String id);
  
  public String[] checkProblem(String problemCode, String problemId, String problemName, int testCase);
  
  public String[] checkProblems(String problemCode, String problemId, String problemName);
  
  public void incrementSolvedCount(String problemId);
}
