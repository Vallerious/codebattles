package com.codebattles.ranking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PagingSortingURLParamsInterceptor extends HandlerInterceptorAdapter {
  
  private static final int PAGE_SIZE = 5;

  @Override
  public boolean preHandle(
    HttpServletRequest request,
    HttpServletResponse response, 
    Object handler) throws Exception {
       
      String queryString = request.getQueryString();
      
      if (queryString == null && request.getRequestURL().indexOf("ranking") > -1) {
        response.sendRedirect("/ranking?page=0&size=" + PAGE_SIZE + "&sort=rating,desc");
      }
       
      return true;
  }
}
