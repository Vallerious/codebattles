package com.codebattles.admin;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;
import com.codebattles.practice.IPracticeProblemService;
import com.codebattles.practice.PracticeProblem;
import com.codebattles.role.Role;
import com.codebattles.user.CodebattlesUser;
import com.codebattles.user.IUserService;
import com.codebattles.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class DashboardController extends BaseController {
  
  @Autowired
  private IUserService userService;
  
  @Autowired
  private IPracticeProblemService practiceProblemService;

  @RequestMapping("/dashboard")
  public ModelAndView index() {
    return this.basicViewWithData("dashboard", new AdminModelView(
        this.userService.getAllUsers(),
        this.practiceProblemService.getProblems(),
        this.getCurrentUser().getId()
        )
    );
  }
  
  @RequestMapping("/dashboard/problem/create")
  public ModelAndView createProblem() {
    return this.basicViewWithData("add-problem", new PracticeProblem());
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
