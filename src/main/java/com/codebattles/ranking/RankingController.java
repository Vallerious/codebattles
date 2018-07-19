package com.codebattles.ranking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

@Controller
public class RankingController extends BaseController {

  @RequestMapping(value = "/ranking", method = RequestMethod.GET)
  public ModelAndView index() {
    return this.basicView("ranking");
  }
}
