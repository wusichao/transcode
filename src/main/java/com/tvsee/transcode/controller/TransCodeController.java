package com.tvsee.transcode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvsee.transcode.entity.Task;
import com.tvsee.transcode.service.TransCodeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="transCode",produces="application/json")
public class TransCodeController {
	private static final Logger log = LoggerFactory.getLogger(TransCodeController.class);
	@Autowired
	private TransCodeService transCodeService;
	@ApiOperation(value="查询转码任务", response=String.class)
	@GetMapping(value="tasks")
	public Object getTasks(@RequestParam(value="start", required=false)String start,
			@RequestParam(value="count", required=false)String count){
		log.info("查询转码任务从{}到{}", start,count);
		return transCodeService.getTasks(start,count);
	}
	@ApiOperation(value="添加转码任务", response=String.class)
	@PostMapping(value="tasks")
	public Object saveTasks(
			@RequestHeader(value="User-Agent", required=false)String agent,
			@RequestParam(value="input", required=true)String input,
			@RequestParam(value="outputs", required=true)List<String> outputs,
			@RequestParam(value="reportUrl", required=true)String reportUrl,
			@RequestParam(value="priority", required=true)String priority,
			@RequestParam(value="templateId", required=true)String templateId,
			@RequestParam(value="description", required=true)String description,
			@RequestParam(value="type", required=true)String type){
		Task task = new Task(agent, input, outputs, reportUrl, priority, templateId, description, type);
		return transCodeService.saveTasks(task);
	}
	@ApiOperation(value="按任务ID调整单个任务", response=String.class)
	@PutMapping(value="tasks/{taskId}")
	public Object updateTaskById(@PathVariable String taskId,
			@RequestParam(value="action", required=true)String action,
			@RequestParam(value="priority", required=false)String priority){
		try {
			transCodeService.updateTaskById(taskId,action,priority);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "ok";
	}
	
	@ApiOperation(value="按组ID调整一组任务", response=String.class)
	@PutMapping(value="tasks")
	public Object updateTaskByGroupId(@RequestParam(value="groupId", required=true)String groupId,
			@RequestParam(value="action", required=true)String action,
			@RequestParam(value="priority", required=false)String priority){
		try {
			transCodeService.updateTaskByGroupId(groupId,action,priority);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "ok";
	}
	@ApiOperation(value="按任务ID删除单个任务", response=String.class)
	@DeleteMapping(value="tasks/{taskId}")
	public Object deleteTaskById(@PathVariable String taskId){
		try {
			transCodeService.deleteTaskById(taskId);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "ok";
	}
	@ApiOperation(value="按组ID删除一组任务", response=String.class)
	@DeleteMapping(value="tasks")
	public Object deleteTaskByGroupId(@RequestParam(value="groupId", required=true)String groupId){
		try {
			transCodeService.deleteTaskByGroupId(groupId);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "ok";
	}
}
