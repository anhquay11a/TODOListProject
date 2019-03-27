package com.project.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.todolist.enums.Direction;
import com.project.todolist.enums.OrderBy;
import com.project.todolist.exception.PaginationSortingException;
import com.project.todolist.exception.PagingSortingErrorResponse;
import com.project.todolist.model.Work;
import com.project.todolist.service.PaginationService;

@RestController
@RequestMapping(value = "/pagination")
public class PaginationController {
	
	@Autowired
	private PaginationService paginationService;
	
	@RequestMapping(value = "/conditionalPagination", params = { "orderBy", "direction", "page", "size" }, method = RequestMethod.GET)
	@ResponseBody
	public Page<Work> findJsonDataByPageAndSize(@RequestParam("orderBy") String orderBy,
														@RequestParam("direction") String direction, @RequestParam("page") int page,
														@RequestParam("size") int size) {
		
		if (!(direction.equals(Direction.ASCENDING.getDirectionCode()) || direction.equals(Direction.DESCENDING.getDirectionCode()))) {
			throw new PaginationSortingException("Invalid sort direction");
		}
		
		if (!(orderBy.equals(OrderBy.WORK_ID.getOrderByCode()) || orderBy.equals(OrderBy.WORK_NAME.getOrderByCode()))) {
			throw new PaginationSortingException("Invalid orderBy condition");
		}
		
		Page<Work> list = paginationService.findListWorksByCondition(orderBy, direction, page, size);
		return list;
	}
	
	@ExceptionHandler(PaginationSortingException.class)
	public ResponseEntity<PagingSortingErrorResponse> exceptionHandler(Exception ex) {
		PagingSortingErrorResponse pagingSortingErrorResponse = new PagingSortingErrorResponse();
		pagingSortingErrorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		pagingSortingErrorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<PagingSortingErrorResponse>(pagingSortingErrorResponse, HttpStatus.OK);
	}

}
