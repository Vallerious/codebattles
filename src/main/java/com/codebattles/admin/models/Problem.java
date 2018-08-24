package com.codebattles.admin.models;

import java.util.List;

public class Problem {
  private String name;
  
  private String description;
  
  private Long points;
  
  private List<InputOutput> inputOutputs;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getPoints() {
    return points;
  }

  public void setPoints(Long points) {
    this.points = points;
  }

  public List<InputOutput> getInputOutputs() {
    return inputOutputs;
  }

  public void setInputOutputs(List<InputOutput> inputOutputs) {
    this.inputOutputs = inputOutputs;
  }
  
}
