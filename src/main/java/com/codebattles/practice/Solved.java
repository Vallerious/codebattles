package com.codebattles.practice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.codebattles.user.CodebattlesUser;

@Entity
@Table(name="solved")
public class Solved {
  
  public Solved() {}

  public Solved(CodebattlesUser user, PracticeProblem problem) {
    super();
    setUser(user);
    setProblem(problem);
  }

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", unique = true)
  private String id;
  
  @ManyToOne
  @JoinColumn(name="user_id")
  private CodebattlesUser user;
  
  @ManyToOne
  @JoinColumn(name="problem_id")
  private PracticeProblem problem;
  
  @Column(name="dateSolved", columnDefinition="DATETIME")
  @CreationTimestamp
  private Date dateSolved;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CodebattlesUser getUser() {
    return user;
  }

  public void setUser(CodebattlesUser user) {
    this.user = user;
  }

  public PracticeProblem getProblem() {
    return problem;
  }

  public void setProblem(PracticeProblem problem) {
    this.problem = problem;
  }

  public Date getDateSolved() {
    return dateSolved;
  }

  public void setDateSolved(Date dateSolved) {
    this.dateSolved = dateSolved;
  }
  
  
}
