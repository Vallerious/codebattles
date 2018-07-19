package com.codebattles.contests;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;

@Controller
public class ContestsController extends BaseController {

  @RequestMapping("/contests")
  public ModelAndView index() {
    return this.basicView("contests");
  }
}
