package com.mebitech.web.security;

import java.nio.charset.Charset;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mebitech.core.api.log.IOperationLogService;
import com.mebitech.core.api.persistence.enums.CrudType;

/**
 * Main filter class which is used to authenticate requests.
 *
 */
public class MebitechAuthenticationFilter extends AuthenticatingFilter {

	private static Logger logger = LoggerFactory.getLogger(MebitechAuthenticationFilter.class);

	@Autowired
	private IOperationLogService logService;

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {

		logger.debug("parsing token from request header");
		HttpServletRequest req = (HttpServletRequest) request;

		final String authorization = req.getHeader("Authorization");
		// logger.error("Auth: " + authorization);
		if (authorization != null && authorization.startsWith("Basic")) {
			// Authorization: Basic base64credentials
			String base64Credentials = authorization.substring("Basic".length()).trim();
			String credentials = new String(DatatypeConverter.parseBase64Binary(base64Credentials),
					Charset.forName("UTF-8"));
			// credentials = username:password
			final String[] values = credentials.split(":", 2);
			// logger.info("Username: " + values[0]);
			// logger.info("Password: " + values[1]);
			return new UsernamePasswordToken(values[0], values[1]);
		}

		String user = req.getHeader("username");
		String pwd = req.getHeader("password");
		// logger.error("IUser:" + user + " pwd: " + pwd);
		if (user == null) {
			user = req.getParameter("username");
			pwd = req.getParameter("password");
		}

		logger.debug("creating usernamepassword token for user: {}, pwd: {}", user, "*****");
		return new UsernamePasswordToken(user, pwd);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		logger.debug("will try to authenticate now...");
		return executeLogin(request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		String user = req.getHeader("username");
		try {
			logService.saveLog(user, CrudType.LOGIN, "Unauthorized access request", null, getHost(request));
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
		}
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return false;
	}

}
