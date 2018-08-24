package com.codebattles.practice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codebattles.admin.models.InputOutput;
import com.codebattles.admin.models.Problem;
import com.codebattles.practice.repositories.PracticeProblemRepository;
import com.codebattles.practice.services.PracticeProblemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticeProblemServiceTest {

  @Autowired
  private PracticeProblemService practiceProblemService;
  
  private Problem problem;
  
  @Before
  public void Init() {
    problem = new Problem();
    problem.setName("TestProblem");
    problem.setDescription("This is a test problem for testing purposes, test");
    problem.setPoints(100L);
    
    List<InputOutput> inputOutputs = new ArrayList<>();
    inputOutputs.add(new InputOutput("1 2", "3"));
    inputOutputs.add(new InputOutput("9 8", "17"));
    inputOutputs.add(new InputOutput("9 28", "37"));
    inputOutputs.add(new InputOutput("opa tropa", "hello"));
    inputOutputs.add(new InputOutput("88 83", "199"));
    
    problem.setInputOutputs(inputOutputs);
  }
  
  @Test
  public void testAddProblem_fullProblemObj_expectToWriteFilesAndDirs() throws IOException {
    this.practiceProblemService.addProblem(this.problem);
    
    String rootPath = System.getProperty("user.dir");
    
    File inputDir = new File(rootPath + "/src/main/resources/problems/" + this.problem.getName() + "/input");
    File outputDir = new File(rootPath + "/src/main/resources/problems/" + this.problem.getName() + "/output");
    
    boolean isThereInputFolder = inputDir.exists();
    boolean isThereOutputFolder = outputDir.exists();
    
    assertEquals(true, isThereInputFolder);
    assertEquals(true, isThereOutputFolder);
    assertEquals(5, inputDir.list().length);
    assertEquals(5, outputDir.list().length);
  }
  
  @Test(expected = IOException.class)
  public void testAddProblem_noNameProblem_shouldThrowWhenNoName() throws IOException {
    this.problem.setName("");
    
    try {
      this.practiceProblemService.addProblem(problem);      
    } finally {
      this.problem.setName("TestProblem");
    }
  }
  
  @Test(expected = IOException.class)
  public void testAddProblem_notFiveTests_shouldThrow() throws IOException {
    List<InputOutput> ios = this.problem.getInputOutputs();
    ios.remove(0);
    
    try {
      this.practiceProblemService.addProblem(problem);      
    } finally {
      this.problem.setName("TestProblem");
    }
  }
}
