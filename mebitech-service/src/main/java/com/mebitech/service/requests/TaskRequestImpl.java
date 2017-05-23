package com.mebitech.service.requests;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mebitech.core.api.rest.enums.DNType;
import com.mebitech.core.api.rest.requests.ITaskRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequestImpl implements ITaskRequest {

	private static final long serialVersionUID = -4476822298585494125L;

	/**
	 * Contains DN entries which are subject to task execution.
	 */
	private List<String> dnList;

	/**
	 * This type indicates what kind of DN entries to consider when executing
	 * tasks. (For example DN list may consists of some OU groups and user may
	 * only want to execute a task on user DN's inside these groups.)
	 */
	private DNType dnType;

	/**
	 * Name of the plugin which executes the task.
	 */
	private String pluginName;

	/**
	 * Version number of the plugin which executes the task.
	 */
	private String pluginVersion;

	/**
	 * Command ID is a unique value in the target plugin that is used to
	 * distinguish an ICommand class from others.
	 */
	private String commandId;

	/**
	 * Custom parameter map that can be used by the plugin.
	 */
	private Map<String, Object> parameterMap;

	/**
	 * If cron expression is not null or empty, then task will be scheduled on
	 * the server.
	 */
	private String cronExpression;

	/**
	 * Optional parameter which can be used to activate the task on this date.
	 * (Task will be sent to server on this date)
	 */
	private Date activationDate;

	/**
	 * Timestamp of the request
	 */
	private Date timestamp;

	public TaskRequestImpl() {
	}

	public TaskRequestImpl(List<String> dnList, DNType dnType, String pluginName, String pluginVersion,
			String commandId, Map<String, Object> parameterMap, String cronExpression, Date activationDate,
			Date timestamp) {
		super();
		this.dnList = dnList;
		this.dnType = dnType;
		this.pluginName = pluginName;
		this.pluginVersion = pluginVersion;
		this.commandId = commandId;
		this.parameterMap = parameterMap;
		this.cronExpression = cronExpression;
		this.activationDate = activationDate;
		this.timestamp = timestamp;
	}

	@Override
	public List<String> getDnList() {
		return dnList;
	}

	public void setDnList(List<String> dnList) {
		this.dnList = dnList;
	}

	@Override
	public DNType getDnType() {
		return dnType;
	}

	public void setDnType(DNType dnType) {
		this.dnType = dnType;
	}

	@Override
	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	@Override
	public String getPluginVersion() {
		return pluginVersion;
	}

	public void setPluginVersion(String pluginVersion) {
		this.pluginVersion = pluginVersion;
	}

	@Override
	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	@Override
	public Map<String, Object> getParameterMap() {
		return parameterMap == null ? new HashMap<String, Object>() : parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	@Override
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	@Override
	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TaskRequestImpl [dnList=" + dnList + ", dnType=" + dnType + ", pluginName=" + pluginName
				+ ", pluginVersion=" + pluginVersion + ", commandId=" + commandId + ", parameterMap=" + parameterMap
				+ ", cronExpression=" + cronExpression + ", activationDate=" + activationDate + ", timestamp="
				+ timestamp + "]";
	}

}
