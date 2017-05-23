package com.mebitech.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by SedaOzcan on 17.02.2017.
 */
@Path("system")
@Consumes({"application/json"})
@Produces({"application/json"})
public class SystemResource {
    public SystemResource() {
    }

    @GET
    @Path("heapdump")
    @Produces({"application/octet-stream"})
    public Response getHeapDump() {

       return null;
    }
}
