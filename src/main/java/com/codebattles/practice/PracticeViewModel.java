package com.codebattles.practice;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PracticeViewModel {
  private String initialSourceCode;
  
  private PracticeProblem practiceProblem;

  public PracticeViewModel() {
    ClassLoader classLoader = getClass().getClassLoader();
    try {
      this.initialSourceCode = FileUtil.readAsString(new File(classLoader.getResource("problems/Main.java").getFile()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public String getInitialSourceCode() {
    return initialSourceCode;
  }

  public void setInitialSourceCode(String initialSourceCode) {
    this.initialSourceCode = initialSourceCode;
  }

  public PracticeProblem getPracticeProblem() {
    return practiceProblem;
  }

  public void setPracticeProblem(PracticeProblem practiceProblem) {
    this.practiceProblem = practiceProblem;
  }
  
}
