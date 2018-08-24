package com.codebattles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.interfaces.ISolvedService;
import com.codebattles.models.CodebattlesUser;
import com.codebattles.modelviews.ProfileViewModel;

@Controller
public class ProfileController extends BaseController {
  
  private ISolvedService solvedService;
  
  @Autowired
  public ProfileController(ISolvedService solvedService) {
    this.solvedService = solvedService;
  }
  
  @RequestMapping("/profile")
  public ModelAndView index() {
    CodebattlesUser user = this.getCurrentUser();
    ProfileViewModel profileViewModel = new ProfileViewModel(
        user,
        this.solvedService.getDateRating(user.getId())
    );
    
    return this.basicViewWithData("profile", profileViewModel);
  }
}
