package com.project.todolist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.project.todolist.model.Work;

import java.util.Date;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoListApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	

	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void testGetAllWorks() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/works",
		HttpMethod.GET, entity, String.class);
		Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testCreateWork() {
		Work work = new Work();
		work.setWorkName("test");
		work.setStartingDate(new Date());
		work.setEndingDate(new Date());
		work.setStatus(1);
		ResponseEntity<Work> postResponse = restTemplate.postForEntity(getRootUrl() + "/works", work, Work.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testUpdateWork() {
		int id = 1;
		Work work = restTemplate.getForObject(getRootUrl() + "/works/" + id, Work.class);
		work.setWorkName("test 2");
		work.setStatus(2);
		restTemplate.put(getRootUrl() + "/works/" + id, work);
		Work updatedUser = restTemplate.getForObject(getRootUrl() + "/works/" + id, Work.class);
		Assert.assertNotNull(updatedUser);
	}

	@Test
	public void testDeleteWork() {
		int id = 2;
		Work work = restTemplate.getForObject(getRootUrl() + "/works/" + id, Work.class);
		Assert.assertNotNull(work);
		restTemplate.delete(getRootUrl() + "/users/" + id);
		try {
			work = restTemplate.getForObject(getRootUrl() + "/users/" + id, Work.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
