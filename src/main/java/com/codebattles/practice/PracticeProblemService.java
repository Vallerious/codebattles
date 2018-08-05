package com.codebattles.practice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PracticeProblemService implements IPracticeProblemService {
  
  private PracticeProblemRepository practiceProblemRepository;
  
  @Autowired
  public PracticeProblemService(PracticeProblemRepository practiceProblemRepository) {
    this.practiceProblemRepository = practiceProblemRepository;
  }

  @Override
  public List<PracticeProblem> getProblems() {
    return this.practiceProblemRepository.findAll();
  }

  @Override
  public PracticeProblem getProblem(String id) {
    return this.practiceProblemRepository.getOne(id);
  }

}
