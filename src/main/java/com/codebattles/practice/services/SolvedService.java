package com.codebattles.practice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebattles.practice.interfaces.ISolvedService;
import com.codebattles.practice.models.PracticeProblem;
import com.codebattles.practice.models.Solved;
import com.codebattles.practice.repositories.PracticeProblemRepository;
import com.codebattles.practice.repositories.SolvedRepository;
import com.codebattles.user.models.CodebattlesUser;

@Service
public class SolvedService implements ISolvedService {
  private SolvedRepository solvedRepository;
  
  private PracticeProblemRepository practiceProblemRepository;
  
  @Autowired
  public SolvedService(SolvedRepository solvedRepository, PracticeProblemRepository practiceProblemRepository) {
    this.solvedRepository = solvedRepository;
    this.practiceProblemRepository = practiceProblemRepository;
  }
  
  public Solved recordSolvedProblem(CodebattlesUser user, PracticeProblem practiceProblem) {
    Solved solved = new Solved(user, practiceProblem);
    
    this.solvedRepository.saveAndFlush(solved);
    
    return solved;
  }

  @Override
  public boolean checkIfProblemSolved(CodebattlesUser user, PracticeProblem practiceProblem) {
    String problemId = this.solvedRepository.findByUserIdAndProblemId(user, practiceProblem);
    
    return problemId != null;
  }

  @Override
  public List<Long[]> getDateRating(String userId) {
    List<Solved> solveds = this.solvedRepository.findByUserId(userId);
    List<Long[]> dateRating = new ArrayList<>();
    
    for (Solved solv : solveds) {
      PracticeProblem practiceProblem = solv.getProblem();
      Long[] dr = {(long) solv.getDateSolved().getTime(), practiceProblem.getPoints()};
      
      dateRating.add(dr);
    }
    
    return dateRating;
  }

}
