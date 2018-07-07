package com.codebattles.contests;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContestsController {

  @RequestMapping("/contests")
  public String index() {
    return "contests";
  }
}
