package com.codebattles.practice;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  @Override
  public boolean checkProblem(String problemCode, String problemId) {
    // Store code on disk with some random name
    Random random = new Random();
    Long randomNameToken = random.nextLong();
    String fileName = problemId + "-" + randomNameToken;
    
    String rootPath = System.getProperty("user.dir");
    File file = new File(rootPath + "/src/main/solutions/" + fileName);
    
    try {
      file.createNewFile();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    FileUtil.writeAsString(file, problemCode);
    // Load the input file as parameter to the executed code
    
    // get the result and compare it to the output file
    
    // return true if the output matches the result from the code run
    return false;
  }

}
