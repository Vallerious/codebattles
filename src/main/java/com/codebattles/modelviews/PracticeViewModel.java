package com.codebattles.modelviews;

import java.io.File;
import java.io.IOException;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;

import com.codebattles.models.PracticeProblem;

@Service
public class PracticeViewModel {
  private String initialSourceCode;
  
  private PracticeProblem practiceProblem;
  
  private String error;

  public PracticeViewModel() {
    this.setInitialSourceCode();
  }

  public String getError() {
    return error;
  }
  
  public void setError(String error) {
    this.error = error;
  }
  
  public String getInitialSourceCode() {
    return initialSourceCode;
  }

  public void setInitialSourceCode() {
    ClassLoader classLoader = getClass().getClassLoader();
    try {
      this.initialSourceCode = FileUtil.readAsString(new File(classLoader.getResource("problems/Main.java").getFile()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public PracticeProblem getPracticeProblem() {
    return practiceProblem;
  }

  public void setPracticeProblem(PracticeProblem practiceProblem) {
    this.practiceProblem = practiceProblem;
  }
  
}
