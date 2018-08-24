package com.codebattles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContestsController extends BaseController {

  @RequestMapping("/contests")
  public ModelAndView index() {
    return this.basicView("contests");
  }
}
