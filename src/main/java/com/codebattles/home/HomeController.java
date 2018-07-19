package com.codebattles.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;

@Controller
public class HomeController extends BaseController {
  @RequestMapping("/")
  public ModelAndView index() {
    return this.basicView("home");
  }
}
