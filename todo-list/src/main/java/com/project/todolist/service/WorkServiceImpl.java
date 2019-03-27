package com.project.todolist.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todolist.dao.WorkDAO;

@Service
public class WorkServiceImpl implements WorkService {
	
	@Autowired
	private WorkDAO WorkDAO;

	@Override
	public Map<String, Object> fetchAll() {
		Map<String, Object> resultList = new HashMap<>();
		resultList.put("resultList", WorkDAO.findAll());
		return resultList;
	}
	
}
