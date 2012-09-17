package fr.insalyon.telecom.mid.todoapp.rest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("todo")
public class Todo {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String list() {
    ObjectMapper mapper = new ObjectMapper();
    StringWriter stringWriter = new StringWriter();
    try {
      mapper.writeValue(stringWriter, Arrays.asList("foo", "bar", "baz"));
      stringWriter.close();
      return stringWriter.toString();
    } catch (IOException ex) {
      Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
      return "";
    }
  }
}
