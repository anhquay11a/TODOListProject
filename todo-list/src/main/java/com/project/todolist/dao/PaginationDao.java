package com.project.todolist.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.todolist.model.Work;

public interface PaginationDao extends PagingAndSortingRepository<Work, Integer> {

}
