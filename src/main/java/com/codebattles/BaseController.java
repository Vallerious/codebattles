package com.codebattles;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
  public ModelAndView basicView(String viewName) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("layouts/default");
    modelAndView.addObject("viewName", viewName);
    
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean hasAdminRole = authentication.getAuthorities().stream()
        .anyMatch(r -> r.getAuthority().equals("ADMIN"));

    modelAndView.addObject("isAdmin", hasAdminRole);

    return modelAndView;
  }
  
  public ModelAndView basicViewWithData(String viewName, Object data) {
    ModelAndView modelAndView = this.basicView(viewName);
    modelAndView.addObject("data", data);
    
    return modelAndView;
  }
}
