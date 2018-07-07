package com.codebattles.ranking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RankingController {

  @RequestMapping("/ranking")
  public String index() {
    return "ranking/index";
  }
}
