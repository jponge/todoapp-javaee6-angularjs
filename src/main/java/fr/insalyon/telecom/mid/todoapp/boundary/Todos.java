package fr.insalyon.telecom.mid.todoapp.boundary;

import fr.insalyon.telecom.mid.todoapp.entity.Todo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Todos implements Serializable {

  @PersistenceContext(unitName = "TodoAppPU")
  EntityManager em;
  
  @Resource(mappedName = "jms/TodoConnectionFactory")
  QueueConnectionFactory jmsConnectionFactory;
  
  @Resource(mappedName = "jms/CompletedTodoQueue")
  Queue queue;

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
      try {
        Connection connection = jmsConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(queue);
        TextMessage message = session.createTextMessage(todo.getText());
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
      } catch (JMSException ex) {
        Logger.getLogger(Todos.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
