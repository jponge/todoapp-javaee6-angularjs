package fr.insalyon.telecom.mid.todoapp.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    return new HashSet<Class<?>>() {
      {
        add(Hello.class);
        add(TodoResource.class);
      }
    };
  }     
}
