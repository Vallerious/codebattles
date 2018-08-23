package com.codebattles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.user.CodebattlesUser;
import com.codebattles.user.UserService;

import net.bytebuddy.asm.Advice.This;

@Controller
public class BaseController {
  
  @Autowired
  private UserService userService;
  
  public ModelAndView basicView(String viewName) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("layouts/default");
    modelAndView.addObject("viewName", viewName);
    modelAndView.addObject("title", viewName);
    
    Authentication authentication = this.getAuth();

    boolean hasAdminRole = authentication
        .getAuthorities()
        .stream()
        .anyMatch(r -> r.getAuthority().equals("ADMIN"));

    modelAndView.addObject("isAdmin", hasAdminRole);

    return modelAndView;
  }
  
  public ModelAndView basicViewWithData(String viewName, Object data) {
    ModelAndView modelAndView = this.basicView(viewName);
    modelAndView.addObject("data", data);
    modelAndView.addObject("title", viewName);
    
    return modelAndView;
  }
  
  public Authentication getAuth() {
    return SecurityContextHolder
        .getContext()
        .getAuthentication();
  }
  
  public CodebattlesUser getCurrentUser() {
    return this.userService.findUserByEmail(this.getAuth().getName());
  }
}
