package com.codebattles.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;
import com.codebattles.practice.interfaces.ISolvedService;
import com.codebattles.user.models.CodebattlesUser;
import com.codebattles.user.models.ProfileViewModel;

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
