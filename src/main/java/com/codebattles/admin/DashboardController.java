package com.codebattles.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;
import com.codebattles.practice.IPracticeProblemService;
import com.codebattles.user.IUserService;

@Controller
public class DashboardController extends BaseController {
  
  @Autowired
  private IUserService userService;
  
  @Autowired
  private IPracticeProblemService practiceProblemService;

  @RequestMapping("/dashboard")
  public ModelAndView index() {
    return this.basicViewWithData("dashboard", new AdminModelView(
        this.userService.getAllUsers(), this.practiceProblemService.getProblems())
    );
  }
}
