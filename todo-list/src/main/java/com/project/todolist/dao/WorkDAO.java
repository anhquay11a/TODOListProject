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
		sql.append(" SELECT * ");
//		sql.append(" ,CASE ");
//		sql.append(" WHEN wk.status = 1 THEN 'Planning' ");
//		sql.append(" WHEN wk.status = 2 THEN 'Doing' ");
//		sql.append(" ELSE 'Complete' ");
//		sql.append(" END  AS status ");
		sql.append(" FROM WORK AS wk ");
		sql.append(" ORDER BY wk.WORK_ID DESC ");
		
		Query query = entityManager.createNativeQuery(sql.toString(), Work.class);
//		System.out.print("1212121212: " + sql.toString());
		
		return query.getResultList();
	}
	
}
