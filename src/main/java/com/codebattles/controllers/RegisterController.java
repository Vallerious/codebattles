package com.codebattles.controllers;

import com.codebattles.models.CodebattlesUser;
import com.codebattles.services.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController extends BaseController {
  
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView register() {
    return this.basicViewWithData("register", new CodebattlesUser());
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView createNewUser(
      @Valid @ModelAttribute CodebattlesUser user,
      BindingResult bindingResult,
      ModelAndView modelAndView
      ) {
      CodebattlesUser userExists = userService.findUserByEmail(user.getEmail());
      
      if (userExists != null) {
          bindingResult
                  .rejectValue("email", "error.user",
                          "*There is already a user registered with the email provided");
      }

      userExists = userService.findUserByUsername(user.getUsername());

      if (userExists != null) {
        bindingResult.rejectValue("username", "error.user", "*There is already a user registered with this username");
      }
      
      if (bindingResult.hasErrors()) {
          modelAndView.setViewName("layouts/default");
          modelAndView.addObject("viewName", "register");
          modelAndView.addObject("data", new CodebattlesUser());
          modelAndView.addObject("errors", bindingResult.getAllErrors());
      } else {
          userService.saveUser(user);
          modelAndView.addObject("successMessage", "User has been registered successfully");
          modelAndView.setViewName("layouts/default");
          modelAndView.addObject("viewName", "login");
      }

      return modelAndView;
  }
}
