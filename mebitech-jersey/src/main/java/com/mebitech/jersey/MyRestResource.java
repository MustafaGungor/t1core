package com.mebitech.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by SedaOzcan on 15.02.2017.
 */
@Path( "/get-data" )
public class MyRestResource {

    @GET
    public Response getDataToDisplay() {
        return Response.ok().entity( "Yes, it works." ).build();
    }
}
