package com.mebitech.web.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mebitech.core.api.log.IOperationLogService;
import com.mebitech.core.api.persistence.enums.CrudType;
import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.processors.IMenuRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;

@Controller
@RequestMapping("/resources/Menu")
public class MenuResourceController {
    private static Logger logger = LoggerFactory.getLogger(MenuResourceController.class);
    @Autowired
    private IResponseFactory responseFactory;
    @Autowired
    private IMenuRequestProcessor menuProcessor;
    @Autowired
    private IOperationLogService logService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public IRestResponse listMenu(@RequestParam(value = "hostname", required = false) String hostname,
                                  @RequestParam(value = "dn", required = false) String dn,
                                  @RequestParam(value = "uid", required = false) String uid, HttpServletRequest request)
            throws UnsupportedEncodingException {
        try {
            
            logService.saveLog(uid, CrudType.READ, "Get Menu List", null, hostname);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        logger.info("Request received. URL: '/mebitech/Menu'", new Object[]{hostname, dn, uid});
        IRestResponse restResponse = menuProcessor.list(hostname, dn, uid);
        logger.debug("Completed processing request, returning result: {}", restResponse.toJson());
        return restResponse;
    }
}