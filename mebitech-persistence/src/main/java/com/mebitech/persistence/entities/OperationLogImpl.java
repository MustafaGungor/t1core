package com.mebitech.persistence.entities;

import java.util.Date;

import javax.persistence.*;

import com.mebitech.core.api.persistence.entities.IOperationLog;
import com.mebitech.core.api.persistence.enums.CrudType;

/**
 * Entity class for IOperationLog objects.
 *
 * @see com.mebitech.core.api.persistence.entities.IOperationLog
 *
 */
@Entity
@Table(name = "OPERATION_LOG")
public class OperationLogImpl implements IOperationLog {

	private static final long serialVersionUID = -241241606291513291L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(generator = "SEQ_OPERATION_LOG", strategy = GenerationType.SEQUENCE)
	//@SequenceGenerator(name = "SEQ_OPERATION_LOG", sequenceName = "SEQ_OPERATION_LOG", allocationSize = 1)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "IS_DELETED")//, columnDefinition="tinyint(1) default 0"
	private Boolean deleted;

	@Column(name = "CRUD_TYPE", length = 1)
	private Integer crudType;

	@Column(name = "TASK_ID")
	private Long taskId;

	@Column(name = "POLICY_ID")
	private Long policyId;

	@Column(name = "PROFILE_ID")
	private Long profileId;

	@Column(name = "LOG_MESSAGE", nullable = false)
	private String logMessage;

	@Lob
	@Column(name = "REQUEST_DATA")
	private byte[] requestData;

	@Column(name = "REQUEST_IP")
	private String requestIp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false)
	private Date createDate;

	public OperationLogImpl() {
	}

	public OperationLogImpl(Long id, String userId, CrudType crudType, Long taskId, Long policyId, Long profileId,
			String logMessage, byte[] requestData, String requestIp, Date createDate) {
		this.id = id;
		this.userId = userId;
		setCrudType(crudType);
		this.taskId = taskId;
		this.policyId = policyId;
		this.profileId = profileId;
		this.logMessage = logMessage;
		this.requestData = requestData;
		this.requestIp = requestIp;
		this.createDate = createDate;
	}

	public OperationLogImpl(IOperationLog log) {
		this.id = log.getId();
		this.userId = log.getUserId();
		setCrudType(log.getCrudType());
		this.taskId = log.getTaskId();
		this.policyId = log.getPolicyId();
		this.profileId = log.getProfileId();
		this.logMessage = log.getLogMessage();
		this.requestData = log.getRequestData();
		this.requestIp = log.getRequestIp();
		this.createDate = log.getCreateDate();
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public CrudType getCrudType() {
		return CrudType.getType(crudType);
	}

	public void setCrudType(CrudType crudType) {
		if (crudType == null) {
			this.crudType = null;
		} else {
			this.crudType = crudType.getId();
		}
	}

	@Override
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Override
	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	@Override
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Override
	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	@Override
	public byte[] getRequestData() {
		return requestData;
	}

	public void setRequestData(byte[] requestData) {
		this.requestData = requestData;
	}

	@Override
	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public Boolean getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
