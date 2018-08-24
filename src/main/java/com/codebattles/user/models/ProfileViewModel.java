package com.codebattles.user.models;

import java.util.List;

public class ProfileViewModel {
  private CodebattlesUser user;
  
  private List<Long[]> dateRating;

  public ProfileViewModel(CodebattlesUser user, List<Long[]> dateRating) {
    super();
    this.user = user;
    this.dateRating = dateRating;
  }

  public CodebattlesUser getUser() {
    return user;
  }

  public void setUser(CodebattlesUser user) {
    this.user = user;
  }

  public List<Long[]> getDateRating() {
    return dateRating;
  }

  public void setDateRating(List<Long[]> dateRating) {
    this.dateRating = dateRating;
  }

}
