package com.mebitech.core.api.rest.requests;

import java.io.Serializable;
import java.util.Date;

/**
 * Base request class for all request types.
 * 

 *
 */
public interface IRequest extends Serializable {

	Date getTimestamp();

}
