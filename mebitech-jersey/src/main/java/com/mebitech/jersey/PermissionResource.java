package com.mebitech.jersey;

import javax.ws.rs.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by SedaOzcan on 17.02.2017.
 */

@Path("/permission")
@Consumes({"application/json"})
@Produces({"application/json"})
public class PermissionResource {

    public PermissionResource() {
    }

    @Path("{roleOid}/menu")
    @GET
    public List<String> getRoleHierarchicalMenu(@PathParam("roleOid") String roleOid) {

        LinkedList menuOids = new LinkedList();

        return menuOids;
    }

    @Path("{roleOid}/menu")
    @PUT

    public List<String> setRoleHierarchicalMenu(@PathParam("roleOid") String roleOid, List<String> items) {

        LinkedList menuOids = new LinkedList();
        return menuOids;
    }

    @Path("{roleOid}/service")
    @GET
    public List<String> getRoleServices(@PathParam("roleOid") String roleOid) {
        LinkedList serviceOids = new LinkedList();

        return serviceOids;
    }

    @Path("{roleOid}/service")
    @PUT
    public List<String> setRoleService(@PathParam("roleOid") String roleOid, List<String> items) {
        LinkedList serviceOids = new LinkedList();

        return serviceOids;
    }

}
