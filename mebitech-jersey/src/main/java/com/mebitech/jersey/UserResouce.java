package com.mebitech.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SedaOzcan on 17.02.2017.
 */
@Path("user")
@Consumes({"application/json"})
@Produces({"application/json"})
public class UserResouce {

    @Path("all")
    @GET
    public List getUsers() {

        LinkedList users = new LinkedList();
        return users;
    }
}
