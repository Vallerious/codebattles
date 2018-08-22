package com.codebattles.practice;

public class PracticeSuccessViewModel {
  private Long pointsWon;
  
  private String userId;
  
  private Long totalPoints;
  
  private String problemName;

  public PracticeSuccessViewModel(Long pointsWon, String userId, Long totalPoints, String problemName) {
    super();
    this.pointsWon = pointsWon;
    this.userId = userId;
    this.totalPoints = totalPoints;
    this.problemName = problemName;
  }

  public Long getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(Long totalPoints) {
    this.totalPoints = totalPoints;
  }

  public String getProblemName() {
    return problemName;
  }

  public void setProblemName(String problemName) {
    this.problemName = problemName;
  }

  public Long getPointsWon() {
    return pointsWon;
  }

  public void setPointsWon(Long pointsWon) {
    this.pointsWon = pointsWon;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
  
}
