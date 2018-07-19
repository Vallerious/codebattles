package com.codebattles.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;

@Controller
public class LoginController extends BaseController {
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView loginView() {
    return this.basicView("login");
  }
}
