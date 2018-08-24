package com.codebattles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView loginView() {
    return this.basicView("login");
  }
}
