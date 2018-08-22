package com.codebattles.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codebattles.user.CodebattlesUser;

@Repository
public interface SolvedRepository extends JpaRepository<Solved, String> {
  @Query(value="select s.id from Solved s where s.user = :user_id and s.problem = :problem_id")
  public String findByUserIdAndProblemId(@Param("user_id") CodebattlesUser user, @Param("problem_id") PracticeProblem problem);
}
