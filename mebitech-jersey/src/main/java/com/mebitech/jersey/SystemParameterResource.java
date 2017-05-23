package com.mebitech.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by SedaOzcan on 17.02.2017.
 */

@Path("systemparameter")
@Produces({"application/json"})
@Consumes({"application/json"})
public class SystemParameterResource {

    public SystemParameterResource() {
    }

    @Path("all")
    @GET
    public List getAll() {
        return null;
    }
}
