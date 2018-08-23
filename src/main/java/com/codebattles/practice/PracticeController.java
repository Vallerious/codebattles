package com.codebattles.practice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;
import com.codebattles.user.CodebattlesUser;
import com.codebattles.user.IUserService;

import net.bytebuddy.asm.Advice.This;

@Controller
public class PracticeController extends BaseController {
  
  private IPracticeProblemService practiceProblemService;
  
  private IUserService userService;
  
  private ISolvedService solvedService;
  
  @Autowired
  public PracticeController(IPracticeProblemService practiceProblemService, IUserService userService, ISolvedService solvedService) {
    this.practiceProblemService = practiceProblemService;
    this.userService = userService;
    this.solvedService = solvedService;
  }
  
  @RequestMapping(value = "/practice")
  public ModelAndView index() {
    Set<String> solvedProblems = this.practiceProblemService.findSolvedProblems(this.getCurrentUser().getId());
    
    PracticeProblemViewModel practiceProblemViewModel = new PracticeProblemViewModel(
        solvedProblems,
        this.practiceProblemService.getProblems()
    );
    
    return this.basicViewWithData("practice", practiceProblemViewModel);
  }
  
  @RequestMapping(value = "/practice/{id}")
  public ModelAndView problem(@PathVariable(value = "id") String id, @RequestParam(value = "error", defaultValue = "") String error) {
    PracticeViewModel practiceViewModel = new PracticeViewModel();
    practiceViewModel.setError(error);
    practiceViewModel.setPracticeProblem(this.practiceProblemService.getProblem(id));
    
    return this.basicViewWithData("problem", practiceViewModel);
  }
  
  @RequestMapping(value = "/practice/{id}", method = RequestMethod.POST)
  public ModelAndView solveProblem(@PathVariable(value = "id") String id, @RequestBody MultiValueMap<String, String> formData) {
    String code = formData.get("initialSourceCode").get(0);
    String problemName = formData.get("practiceProblem.name").get(0);

    String[] res = this.practiceProblemService.checkProblems(code, id, problemName);
    
    String status = res[0];
    
    if (status.equals("success")) {
      PracticeProblem practiceProblem = this.practiceProblemService.getProblem(id);
      CodebattlesUser user = this.getCurrentUser();
      boolean isProblemAlreadySolved = this.solvedService.checkIfProblemSolved(user, practiceProblem);
      
      if (!isProblemAlreadySolved) {
        this.userService.updateUserScore(user.getEmail(), practiceProblem.getPoints());
        this.solvedService.recordSolvedProblem(user, practiceProblem);
        this.practiceProblemService.incrementSolvedCount(id);
      }

      PracticeSuccessViewModel practiceSuccessViewModel = new PracticeSuccessViewModel(
          isProblemAlreadySolved ? 0 : practiceProblem.getPoints(),
          user.getId(),
          user.getRating(),
          problemName
      );

      return this.basicViewWithData("results", practiceSuccessViewModel);
    } else {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("redirect:/practice/" + id);
      modelAndView.addObject("error", res[1]);
      
      return modelAndView;
    }
  }
}
