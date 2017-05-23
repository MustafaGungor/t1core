package com.mebitech.jersey;

/**
 * Created by SedaOzcan on 17.02.2017.
 */

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("trigger")
@Consumes({"application/json"})
@Produces({"application/json"})
public class TriggerResource {


    @GET
    public List getAll() {
        return null;
    }
}
