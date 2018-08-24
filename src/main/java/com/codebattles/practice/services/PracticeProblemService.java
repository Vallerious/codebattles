package com.codebattles.practice.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codebattles.admin.models.InputOutput;
import com.codebattles.admin.models.Problem;
import com.codebattles.practice.interfaces.IPracticeProblemService;
import com.codebattles.practice.models.PracticeProblem;
import com.codebattles.practice.repositories.PracticeProblemRepository;
import com.codebattles.practice.repositories.SolvedRepository;

@Service
public class PracticeProblemService implements IPracticeProblemService {

  private PracticeProblemRepository practiceProblemRepository;
  
  private SolvedRepository solvedRepository;

  @Autowired
  public PracticeProblemService(PracticeProblemRepository practiceProblemRepository, SolvedRepository solvedRepository) {
    this.practiceProblemRepository = practiceProblemRepository;
    this.solvedRepository = solvedRepository;
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
  public String[] checkProblem(String problemCode, String problemId, String problemName, int testCase) {
    // Store code on disk with some random name
    String[] res = new String[2];

    long randomNameToken = System.currentTimeMillis();
//    String fileName = problemId + randomNameToken;
    String fileName = "Main" + randomNameToken;

    String rootPath = System.getProperty("user.dir");
    String path = rootPath + "/src/main/solutions/" + fileName + ".java";
    File file = new File(path);
    file.delete();

    try {
      file.createNewFile();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    problemCode = problemCode.replaceAll("Main", fileName);
    FileUtil.writeAsString(file, problemCode);
    // Load the input file as parameter to the executed code
    Runtime runtime = Runtime.getRuntime();
    try {
      Process compileProcess = runtime.exec("javac src/main/solutions/" + fileName + ".java");
      compileProcess.waitFor();
      
      BufferedReader javaCompileError = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
      
      String compileErrString = javaCompileError.readLine();
      
      if (compileErrString != null) {
        res[0] = "fail";
        res[1] = compileErrString;
        
        return res;
      }
      
      ClassLoader classLoader = getClass().getClassLoader();
      String in = FileUtil.readAsString(new File(classLoader.getResource("problems/" + problemName + "/input/test0" + testCase + ".txt").getFile()));
      Process process = runtime.exec("java -classpath src/main/solutions " + fileName + " " + in);
      
      BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
      BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String line = null;

      try {
        line = input.readLine();
        String errorMsg = error.readLine();
        
        if (errorMsg != null) {
          res[0] = "fail";
          res[1] = errorMsg;
          
          return res;
        }
        
        String correctOutput = FileUtil.readAsString(new File(classLoader.getResource("problems/" + problemName + "/output/test0" + testCase +".txt").getFile()));
        
        if (correctOutput.equals(line)) {
          System.out.println("Correct result");
          res[0] = "success";
        } else {
          System.out.println("Incorrect!");
          res[0] = "fail";
          res[1] = "Incorrect result!";
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    // Delete the files 
    file.delete();
    File classFile = new File(rootPath + "/src/main/solutions/" + fileName + ".class");
    classFile.delete();

    // return true if the output matches the result from the code run
    return res;
  }

  @Override
  public String[] checkProblems(String problemCode, String problemId, String problemName) {
    String[] reString = new String[2];
    int successes = 0;

    for (int i = 1; i <= 5; i++) {
      String[] res = this.checkProblem(problemCode, problemId, problemName, i);
      
      if (res[0].equals("success")) {
        successes++;
      } else {
        if (!res[1].equals("Incorrect result!")) {
          reString = res;
          return reString;
        }
      }
    }
    
    if (successes == 5) {
      reString[0] = "success";
    } else {
      reString[0] = "fail";
      reString[1] = successes + "/5 correct Answers"; 
    }
    
    return reString;
   
  }

  @Override
  public void incrementSolvedCount(String problemId) {
    PracticeProblem practiceProblem = this.practiceProblemRepository.getOne(problemId);
    
    if (practiceProblem != null) {
      practiceProblem.setSolvedCount(practiceProblem.getSolvedCount() + 1);
      this.practiceProblemRepository.saveAndFlush(practiceProblem);
    }
  }

  @Override
  public void addProblem(Problem problem) {
    // Lets first save the test cases
    
    // 1. Create the folders
    String rootPath = System.getProperty("user.dir");
    new File(rootPath + "/src/main/resources/problems/" + problem.getName() + "/input").mkdirs();
    new File(rootPath + "/src/main/resources/problems/" + problem.getName() + "/output").mkdirs();
    
    // 2. Save the test cases
    List<InputOutput> ios = problem.getInputOutputs();
    
    for (int i = 1; i <= ios.size(); i++) {
      File fileInput = new File(rootPath + "/src/main/resources/problems/" + problem.getName() + "/input/test0" + i + ".txt");
      File fileOutput = new File(rootPath + "/src/main/resources/problems/" + problem.getName() + "/output/test0" + i + ".txt");
      
      try {
        fileInput.createNewFile();
        fileOutput.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      FileUtil.writeAsString(fileInput, ios.get(i - 1).getInputValue());
      FileUtil.writeAsString(fileOutput, ios.get(i - 1).getOutputValue());
    }
    
    // 3. Save the problem to DB
    PracticeProblem practiceProblem = new PracticeProblem();
    practiceProblem.setName(problem.getName());
    practiceProblem.setDescription(problem.getDescription());
    practiceProblem.setPoints(problem.getPoints());
    practiceProblem.setSolvedCount(0L);
    
    this.practiceProblemRepository.saveAndFlush(practiceProblem);
  }

  @Override
  public Set<String> findSolvedProblems(String userId) {
    Set<String> ids = this.solvedRepository.findByUserId(userId)
        .stream()
        .map(s -> s.getProblem().getId())
        .collect(Collectors.toSet());

    return ids;
  }

}
