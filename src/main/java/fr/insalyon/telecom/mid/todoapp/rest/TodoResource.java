package fr.insalyon.telecom.mid.todoapp.rest;

import fr.insalyon.telecom.mid.todoapp.boundary.Todos;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

@Path("todo")
@Stateless
public class TodoResource {
  
  @Inject
  private Todos todos;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String list() {
    ObjectMapper mapper = new ObjectMapper();
    StringWriter stringWriter = new StringWriter();
    try {
      mapper.writeValue(stringWriter, todos.all());
      stringWriter.close();
      return stringWriter.toString();
    } catch (IOException ex) {
      Logger.getLogger(TodoResource.class.getName()).log(Level.SEVERE, null, ex);
      return "[]";
    }
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void create(@FormParam("text") String text) {
    todos.add(text);
  }
    
  @Path("{id}")
  @DELETE  
  public void delete(@PathParam("id") Long id) {
    todos.delete(id);
  }
  
  @Path("{id}")
  @PUT
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void update(@PathParam("id") Long id, @FormParam("done") String done) {
    todos.mark(id, "true".equals(done));
  }
}
