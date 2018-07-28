package com.codebattles.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("practiceProblemRepository")
public interface PracticeProblemRepository extends JpaRepository<PracticeProblem, String> {

}
