package com.codebattles.practice;

import java.util.List;
import java.util.Set;

public class PracticeProblemViewModel {
  private Set<String> solvedProblems;
  
  private List<PracticeProblem> problems;

  public PracticeProblemViewModel(Set<String> solvedProblems, List<PracticeProblem> problems) {
    super();
    this.solvedProblems = solvedProblems;
    this.problems = problems;
  }

  public Set<String> getSolvedProblems() {
    return solvedProblems;
  }

  public void setSolvedProblems(Set<String> solvedProblems) {
    this.solvedProblems = solvedProblems;
  }

  public List<PracticeProblem> getProblems() {
    return problems;
  }

  public void setProblems(List<PracticeProblem> problems) {
    this.problems = problems;
  }
  
}
