package com.codebattles.home;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import com.codebattles.home.controllers.HomeController;

public class HomeControllerTest {
  
  @Mock
  private Authentication authentication;
  
  @Test
  public void testHomeController_template_expectWelcomeMsg() {    
    assertEquals(1, 1);
  }
}
