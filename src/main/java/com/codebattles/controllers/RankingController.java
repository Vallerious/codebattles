package com.codebattles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.interfaces.IUserService;
import com.codebattles.models.CodebattlesUser;

@Controller
public class RankingController extends BaseController {
  
  private IUserService userService;
  
  @Autowired
  public RankingController(IUserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/ranking", method = RequestMethod.GET)
  public ModelAndView index(Pageable pageable) {
    Page<CodebattlesUser> users = this.userService.listAllByPage(pageable);

    return this.basicViewWithData("ranking", users);
  }
}
