package com.codebattles.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

  @RequestMapping
  public String index() {
    return "register/index";
  }
}
