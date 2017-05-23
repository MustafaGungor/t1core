package com.mebitech.jersey;

import javax.ws.rs.*;
import java.util.*;

/**
 * Created by SedaOZcan on 17.02.2017.
 */
@Path("menu")
@Consumes({"application/json"})
@Produces({"application/json"})
public class MenuResource {
    public MenuResource() {
    }

    @Path("all")
    @GET
    public List getMenus() {
        ArrayList menus = new ArrayList();
        return menus;
    }

    @Path("user")
    @GET
    public List getUserHierarchicalMenu() {

        LinkedList permittedItems1 = new LinkedList();
        return permittedItems1;
    }

    @Path("roots")
    @GET
    public List getHierarchicalMenu() {
        return null;
    }

}
