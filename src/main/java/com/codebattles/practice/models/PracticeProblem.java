package com.codebattles.practice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "practice_problems")
public class PracticeProblem {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", unique = true)
  private String id;
  
  private String name;
  
  @Column(name = "solved_count", columnDefinition = "Decimal(10, 2) default '0'")
  private Long solvedCount;
  
  private String description;
  
  private Long points;

  public Long getPoints() {
    return points;
  }

  public void setPoints(Long points) {
    this.points = points;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getSolvedCount() {
    return solvedCount;
  }

  public void setSolvedCount(Long solvedCount) {
    this.solvedCount = solvedCount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
}
