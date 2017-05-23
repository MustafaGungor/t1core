package com.mebitech.jersey;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Calendar;

/**
 * Created by Seda on 17.02.2017.
 */
@Path("ticket")
@Consumes({"text/html"})
@Produces({"text/html"})
public class TicketResource {

    public TicketResource() {
    }

    @GET
    @Path("{ticketOid}")
    public Response getView(@PathParam("ticketOid") String tickedOid, @Context UriInfo uriInfo) {

        return null;
    }
}
