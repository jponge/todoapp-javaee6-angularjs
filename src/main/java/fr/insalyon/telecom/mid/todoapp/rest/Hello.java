package fr.insalyon.telecom.mid.todoapp.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("/hello")
public class Hello {

  @Context
  private UriInfo context;

  @GET
  @Produces("application/json")
  public String getJson() {
    return "{\"foo\":\"bar\"}";
  }
}
