package com.codebattles.practice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;

import net.bytebuddy.asm.Advice.This;

@Controller
public class PracticeController extends BaseController {
  
  private IPracticeProblemService practiceProblemService;
  
  @Autowired
  public PracticeController(IPracticeProblemService practiceProblemService) {
    this.practiceProblemService = practiceProblemService;
  }
  
  @RequestMapping(value = "/practice")
  public ModelAndView index() {
    return this.basicViewWithData("practice", this.practiceProblemService.getProblems());
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
    
    System.out.print(res[0] + " ; " + res[1]);
    
    String status = res[0];
    
    if (status.equals("success")) {
      return this.basicView("results");
    } else {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("redirect:/practice/" + id);
      modelAndView.addObject("error", res[1]);
      
      return modelAndView;
    }
  }
}
