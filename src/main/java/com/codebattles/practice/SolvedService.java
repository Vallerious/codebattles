package com.codebattles.practice;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.websocket.Session;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebattles.user.CodebattlesUser;
import com.mysql.fabric.xmlrpc.base.Array;

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
