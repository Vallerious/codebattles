package com.codebattles.admin.controllers;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;
import com.codebattles.admin.models.AdminViewModel;
import com.codebattles.admin.models.Problem;
import com.codebattles.practice.interfaces.IPracticeProblemService;
import com.codebattles.practice.models.PracticeProblem;
import com.codebattles.role.models.Role;
import com.codebattles.user.interfaces.IUserService;
import com.codebattles.user.models.CodebattlesUser;
import com.codebattles.user.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class DashboardController extends BaseController {

  private IUserService userService;

  private IPracticeProblemService practiceProblemService;
  
  @Autowired
  public DashboardController(IUserService userService, IPracticeProblemService practiceProblemService) {
    this.userService = userService;
    this.practiceProblemService = practiceProblemService;
  }

  @RequestMapping("/dashboard")
  public ModelAndView index() {
    return this.basicViewWithData("dashboard", new AdminViewModel(
        this.userService.getAllUsers(),
        this.practiceProblemService.getProblems(),
        this.getCurrentUser().getId()
        )
    );
  }
  
  @RequestMapping("/dashboard/problem/create")
  public ModelAndView getCreateProblemPage() {
    return this.basicViewWithData("add-problem", new PracticeProblem());
  }
  
  @ResponseBody
  @RequestMapping(value="/dashboard/problem/create", method=RequestMethod.POST)
  public String createProblem(@RequestBody Problem problem) {
    try {
      this.practiceProblemService.addProblem(problem);
    } catch (IOException e) {
      return "Sorry we could not add the problem";
    }
    
    return "pesho";
  }
  
  @RequestMapping(value="/dashboard/user/promote/{id}", method=RequestMethod.POST)
  public String promoteUser(@PathVariable String id) {
    this.userService.changeUserRole(id, 1);
    
    return "redirect:/dashboard";
  }
  
  @RequestMapping(value="/dashboard/user/demote/{id}", method=RequestMethod.POST)
  public String demoteUser(@PathVariable String id) {
    this.userService.changeUserRole(id, -1);
    
    return "redirect:/dashboard";
  }
  
  @RequestMapping(value="/dashboard/user/delete/{id}", method=RequestMethod.POST)
  public String deleteUser(@PathVariable String id) {
    this.userService.deleteUser(id);
    
    return "redirect:/dashboard";
  }
}
