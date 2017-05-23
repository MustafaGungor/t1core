package com.mebitech.jersey;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

/**
 * Created by SedaOzcan on 17.02.2017.
 */
@Path("authentication")
@Produces({"application/json"})
@Consumes({"application/json"})
public class AuthResource {

    @POST
    @Path("login")
    public Response login(Map<String, String> credentials) throws Exception {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("logout")
    public Response logout() throws Exception {
        return null;
    }

    @POST
    @Path("changepassword")
    public Response changePassword(@FormParam("oldPassword") String oldPassword, @FormParam("newPassword") String newPassword, @FormParam("newPassword2") String newPassword2) {

        return Response.ok().build();
    }

    @POST
    @Path("forgotpassword/{forgotEmail}")
    public Response forgotPassword(@PathParam("forgotEmail") String forgotEmail, @Context UriInfo uriInfo) {

        return null;
    }
}
