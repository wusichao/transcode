package com.tvsee.transcode.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tvsee.transcode.entity.Task;

@Service
public class TransCodeService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired  
    private Environment env;  
	public Object getTasks(String start, String count){
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("count", count);
		return restTemplate.getForEntity(env.getProperty("saveTasks")+"?start={start}&count={count}", String.class,params);
	}
	public Object saveTasks(Task task) {
		Object[] param={task};
		return restTemplate.postForEntity(env.getProperty("saveTasks"),param, String.class);
	}
	public void updateTaskById(String taskId, String action, String priority) {
		Task task = new Task();
		task.setAction(action);
		task.setPriority(priority);
		restTemplate.put(env.getProperty("updateTaskById"),task,taskId);
	}
	public void updateTaskByGroupId(String groupId, String action, String priority) {
		Task task = new Task();
		task.setAction(action);
		task.setPriority(priority);
		restTemplate.put(env.getProperty("updateTaskByGroupId"),task,groupId);
	}
	public void deleteTaskById(String taskId) {
		restTemplate.delete(env.getProperty("deleteTaskById"),taskId);
		
	}
	public void deleteTaskByGroupId(String groupId) {
		restTemplate.delete(env.getProperty("deleteTaskByGroupId"),groupId);
		
	}

}
