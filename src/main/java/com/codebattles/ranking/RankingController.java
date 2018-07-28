package com.codebattles.ranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;
import com.codebattles.user.User;
import com.codebattles.user.UserViewModel;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

@Controller
public class RankingController extends BaseController {
  
  @Autowired
  private UserViewModel userViewModel;

  @RequestMapping(value = "/ranking", method = RequestMethod.GET)
  public ModelAndView index(Pageable pageable) {
    Page<User> users = this.userViewModel.getAllUsersForTable(pageable);
    
    return this.basicViewWithData("ranking", users);
  }
}
