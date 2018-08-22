package com.codebattles.practice;

import javax.management.Query;
import javax.websocket.Session;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebattles.user.CodebattlesUser;

@Service
public class SolvedService implements ISolvedService {
  private SolvedRepository solvedRepository;
  
  @Autowired
  public SolvedService(SolvedRepository solvedRepository) {
    this.solvedRepository = solvedRepository;
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
}
