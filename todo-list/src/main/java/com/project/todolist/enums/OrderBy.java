package com.project.todolist.enums;

public enum OrderBy {
	
	WORK_ID("workId"), 
	WORK_NAME("workName");

	private String OrderByCode;
	
	private OrderBy(String orderBy) {
		this.OrderByCode = orderBy;
	}
	
	public String getOrderByCode() {
		return this.OrderByCode;
	}
	
}
