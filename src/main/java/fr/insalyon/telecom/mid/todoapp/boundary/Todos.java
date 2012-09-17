package fr.insalyon.telecom.mid.todoapp.boundary;

import fr.insalyon.telecom.mid.todoapp.entity.Todo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Todos implements Serializable {

  @PersistenceContext(unitName="TodoAppPU")
  EntityManager em;
    
  public List<Todo> all() {    
    return em.createQuery("SELECT t from Todo t", Todo.class).getResultList();
  }
  
  public void mark(Long id, boolean status) {
    Todo todo = em.find(Todo.class, id);
    if (todo != null) {
      todo.setDone(status);
      em.merge(todo);
    }
  }
  
  public void add(String text) {
    em.persist(new Todo(text, false));
  }
  
  public void delete(Long id) {
    Todo todo = em.find(Todo.class, id);
    if (todo != null) {
      em.remove(todo);
    }
  }
}
