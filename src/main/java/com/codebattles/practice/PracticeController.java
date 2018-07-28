package com.codebattles.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;

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
}
