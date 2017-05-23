package com.mebitech.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by SedaOzcan on 17.02.2017.
 */

@Path("role")
@Consumes({"application/json"})
@Produces({"application/json"})
public class RoleResource {

    public RoleResource() {
    }

    @Path("all")
    @GET
    public List getRoles() {
        return null;
    }

}
