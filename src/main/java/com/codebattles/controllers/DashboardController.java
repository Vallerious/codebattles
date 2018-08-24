package com.codebattles.controllers;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.interfaces.IPracticeProblemService;
import com.codebattles.interfaces.IUserService;
import com.codebattles.models.CodebattlesUser;
import com.codebattles.models.PracticeProblem;
import com.codebattles.models.Problem;
import com.codebattles.modelviews.AdminViewModel;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {

  private IUserService userService;

  private IPracticeProblemService practiceProblemService;
  
  @Autowired
  public DashboardController(IUserService userService, IPracticeProblemService practiceProblemService) {
    this.userService = userService;
    this.practiceProblemService = practiceProblemService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView index() {
    List<CodebattlesUser> users = this.userService.getAllUsers();
    
    return this.basicViewWithData("dashboard", new AdminViewModel(
        users,
        this.practiceProblemService.getProblems(),
        this.getCurrentUser().getId(),
        this.userService.getUserRoles(users)
        )
    );
  }
  
  @RequestMapping(value = "/problem/create", method =  RequestMethod.GET)
  public ModelAndView getCreateProblemPage() {
    return this.basicViewWithData("add-problem", new PracticeProblem());
  }
  
  @ResponseBody
  @RequestMapping(value="/problem/create", method=RequestMethod.POST)
  public String createProblem(@RequestBody Problem problem) {
    try {
      this.practiceProblemService.addProblem(problem);
      this.practiceProblemService.saveProblemToDB(problem);
    } catch (IOException e) {
      return "Sorry we could not add the problem";
    }
    
    return "pesho";
  }
  
  @RequestMapping(value="/user/role/{id}/{inc}", method=RequestMethod.POST)
  public String changeRole(@PathVariable String id, @PathVariable Integer inc) {
    this.userService.changeUserRole(id, inc);
    
    return "redirect:/dashboard";
  }
  
  @RequestMapping(value="/user/delete/{id}", method=RequestMethod.POST)
  public String deleteUser(@PathVariable String id) {
    this.userService.deleteUser(id);
    
    return "redirect:/dashboard";
  }
}
