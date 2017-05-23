package com.mebitech.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mebitech.core.api.persistence.entities.IUserSession;
import com.mebitech.core.api.persistence.enums.SessionEvent;

/**
 * Entity class for user login/logout events.
 * 
 * @see com.mebitech.core.api.persistence.entities.IUserSession
 *
 */
@Entity
@Table(name = "C_USER_SESSION")
public class UserSessionImpl implements IUserSession {

	private static final long serialVersionUID = -9102362700808969139L;

	@Id
	@GeneratedValue
	@Column(name = "USER_SESSION_ID", nullable = false)
	private Long id;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "SESSION_EVENT", nullable = false, length = 1)
	private Integer sessionEvent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false)
	private Date createDate;

	public UserSessionImpl() {
	}

	public UserSessionImpl(Long id, String username, SessionEvent sessionEvent, Date createDate) {
		super();
		this.id = id;
		this.username = username;
		setSessionEvent(sessionEvent);
		this.createDate = createDate;
	}

	public UserSessionImpl(IUserSession userSession) {
		this.id = userSession.getId();
		this.username = userSession.getUsername();
		setSessionEvent(userSession.getSessionEvent());
		this.createDate = userSession.getCreateDate();
		
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public SessionEvent getSessionEvent() {
		return SessionEvent.getType(sessionEvent);
	}

	public void setSessionEvent(SessionEvent sessionEvent) {
		if (sessionEvent == null) {
			this.sessionEvent = null;
		} else {
			this.sessionEvent = sessionEvent.getId();
		}
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UserSessionImpl [id=" + id + ", username=" + username + ", sessionEvent=" + sessionEvent
				+ ", createDate=" + createDate + "]";
	}

	@Override
	public Boolean getDeleted() {
		return null;
	}

	@Override
	public void setDeleted(Boolean deleted) {

	}
}
