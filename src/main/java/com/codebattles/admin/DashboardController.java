package com.codebattles.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;

@Controller
public class DashboardController extends BaseController {

  @RequestMapping("/dashboard")
  public ModelAndView index() {
    return this.basicView("dashboard");
  }
}
