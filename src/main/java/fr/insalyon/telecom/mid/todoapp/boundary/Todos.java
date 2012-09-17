package fr.insalyon.telecom.mid.todoapp.boundary;

import fr.insalyon.telecom.mid.todoapp.entity.Todo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Todos implements Serializable {

  private long nextId = 4L;
  
  private final List<Todo> todos = new ArrayList<Todo>() {
    {
      add(new Todo(1L, "Wash the car", false));
      add(new Todo(2L, "Open a bank account in Switzerland", false));
      add(new Todo(3L, "Sleep", true));
    }
  };
  
  public List<Todo> all() {
    return Collections.unmodifiableList(todos);
  }
  
  public void mark(Long id, boolean status) {
    for (Todo todo : todos) {
      if (id.equals(todo.getId())) {
        todo.setDone(status);
        return;
      }
    }
  }
  
  public void add(String text) {
    todos.add(new Todo(nextId, text, false));
    nextId = nextId + 1;
  }
  
  public void delete(Long id) {
    Iterator<Todo> it = todos.iterator();
    while (it.hasNext()) {
      Todo todo = it.next();
      if (id.equals(todo.getId())) {
        it.remove();
        return;
      }
    }
  }
}
