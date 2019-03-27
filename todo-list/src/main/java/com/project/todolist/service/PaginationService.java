package com.project.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project.todolist.dao.PaginationDao;
import com.project.todolist.model.Work;

@Service
public class PaginationService {
	
	@Autowired
	private PaginationDao paginationDao;
	
	@SuppressWarnings("deprecation")
	public Page<Work> findListWorksByCondition(String orderBy, String direction, int page, int size) {
		Sort sort = null;
		if (direction.equals("ASC")) {
			sort = new Sort(new Sort.Order(Direction.ASC, orderBy));
		}
		if (direction.equals("DESC")) {
		sort = new Sort(new Sort.Order(Direction.DESC, orderBy));
		}
		Pageable pageable = new PageRequest(page, size, sort);
		Page<Work> data = paginationDao.findAll(pageable);
		
//		Page<Work> data = (Page<Work>) paginationDao.findAll(new Sort(Sort.Direction.DESC, orderBy));
		return data;
	}

}
