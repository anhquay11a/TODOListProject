package com.project.todolist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.todolist.model.Work;

@Repository
public class WorkDAO {
	
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Work> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT wk.work_id, wk.work_name, wk.starting_date, wk.ending_date, wk.status ");
		sql.append(" FROM WORK AS wk ");
		sql.append(" ORDER BY wk.WORK_ID DESC ");
		Query query = entityManager.createNativeQuery(sql.toString(), Work.class);
		
		return query.getResultList();
	}
	
}
