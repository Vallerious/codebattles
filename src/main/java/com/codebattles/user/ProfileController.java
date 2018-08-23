package com.codebattles.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.BaseController;

import net.bytebuddy.asm.Advice.This;

@Controller
public class ProfileController extends BaseController {
  @RequestMapping("/profile")
  public ModelAndView index() {
    return this.basicViewWithData("profile", this.getCurrentUser());
  }
}
