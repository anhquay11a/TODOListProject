package com.project.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.todolist.model.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

}
