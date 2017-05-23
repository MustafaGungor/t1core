package com.mebitech.service.requests;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mebitech.core.api.rest.enums.DNType;
import com.mebitech.core.api.rest.requests.IPolicyExecutionRequest;

/**
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyExecutionRequestImpl implements IPolicyExecutionRequest {

	private static final long serialVersionUID = -4023348875434687232L;

	private Long id;

	private List<String> dnList;

	private DNType dnType;

	private Date activationDate;

	private Date timestamp;

	public PolicyExecutionRequestImpl() {
	}

	public PolicyExecutionRequestImpl(Long id, List<String> dnList, DNType dnType, Date activationDate,
			Date timestamp) {
		super();
		this.id = id;
		this.dnList = dnList;
		this.dnType = dnType;
		this.activationDate = activationDate;
		this.timestamp = timestamp;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
