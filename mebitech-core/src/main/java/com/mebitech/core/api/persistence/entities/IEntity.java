package com.mebitech.core.api.persistence.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Base entity class for all entities.
 * 
 *
 */
public interface IEntity<T> extends Serializable {

	/**
	 * 
	 * @return record ID
	 */
	Long getId();

	/**
	 * 
	 * @return record creation date
	 */
	Date getCreateDate();

	Boolean getDeleted();

	void setDeleted(Boolean deleted);

	void setCreateDate(Date createDate);



}
