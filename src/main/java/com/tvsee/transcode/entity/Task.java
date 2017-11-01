package com.tvsee.transcode.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task_id;
	private String agent;
	private String input;
	private List<String> outputs;
	private String report_url;
	private String priority;
	private String template_id;
	private String description;
	private String type;
	
	private String subtitle_url="static-dir/sample/test.ass";
	private boolean subtitle_enabled;
	private boolean aliyun_oss;
	private boolean qcloud_cos;
	
	private Map<String, String> hls;
	private Map<String, Object> ts_params;
	private String action;
	
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getSubtitle_url() {
		return subtitle_url;
	}
	public void setSubtitle_url(String subtitle_url) {
		this.subtitle_url = subtitle_url;
	}
	public boolean isSubtitle_enabled() {
		return subtitle_enabled;
	}
	public void setSubtitle_enabled(boolean subtitle_enabled) {
		this.subtitle_enabled = subtitle_enabled;
	}
	public boolean isAliyun_oss() {
		return aliyun_oss;
	}
	public void setAliyun_oss(boolean aliyun_oss) {
		this.aliyun_oss = aliyun_oss;
	}
	public boolean isQcloud_cos() {
		return qcloud_cos;
	}
	public void setQcloud_cos(boolean qcloud_cos) {
		this.qcloud_cos = qcloud_cos;
	}
	public Map<String, String> getHls() {
		return hls;
	}
	public void setHls(Map<String, String> hls) {
		this.hls = hls;
	}
	public Map<String, Object> getTs_params() {
		return ts_params;
	}
	public void setTs_params(Map<String, Object> ts_params) {
		this.ts_params = ts_params;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
	public List<String> getOutputs() {
		return outputs;
	}
	public void setOutputs(List<String> outputs) {
		this.outputs = outputs;
	}

	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getReport_url() {
		return report_url;
	}
	public void setReport_url(String report_url) {
		this.report_url = report_url;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public Task() {
		super();
	}
	public Task(String agent, String input, List<String> outputs, String report_url, String priority,
			String template_id, String description, String type) {
		super();
		this.agent = agent;
		this.input = input;
		this.outputs = outputs;
		this.report_url = report_url;
		this.priority = priority;
		this.template_id = template_id;
		this.description = description;
		this.type = type;
		this.hls=new HashMap<>();
		hls.put("ts_prefix", "1_");
		hls.put("ts_duration", "3");
		this.ts_params = new HashMap<>();
		ts_params.put("muxrate_enabled", false);
		ts_params.put("mux_rate", 1000);
	}
	
}

