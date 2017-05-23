package com.mebitech.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by SedaOzcan on 17.02.2017.
 */
@Path("mailtemplate")
@Consumes({"application/json"})
@Produces({"application/json"})
public class MailTemplateResource {

    public MailTemplateResource() {
    }

    @GET
    @Path("/all")
    public List getAll() {
        return null;
    }


}
