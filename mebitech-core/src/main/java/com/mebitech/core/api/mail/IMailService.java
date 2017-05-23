package com.mebitech.core.api.mail;

import com.mebitech.core.api.configuration.IConfigurationService;
import org.quartz.Job;

import java.util.List;

/**
 * 
 *
 */
public interface IMailService extends Job {

	/**
	 * Send mail to provided list.
	 * 
	 * @param toList
	 * @param subject
	 * @param body
	 */
	void sendMail(List<String> toList,  List<String> attachment, String subject, String body);

	/**
	 * Send mail with specified contenty type to provided list.
	 * 
	 * @param toList
	 * @param subject
	 * @param body
	 * @param contentType
	 */
	void sendMail(List<String> toList,  List<String> attachment, String subject, String body, String contentType);

	void sendMail(List<String> toList, String subject, String body, String contentType);

	void setMailList(List<String> mailList);

	void setObj(String obj);

	void setSubj(String subj);

	void setConfigurationService(IConfigurationService configurationService);
}
