package com.codebattles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebattles.models.PracticeProblem;

@Repository("practiceProblemRepository")
public interface PracticeProblemRepository extends JpaRepository<PracticeProblem, String> {

}
