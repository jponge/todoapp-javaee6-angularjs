package fr.insalyon.telecom.mid.todoapp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo implements Serializable {
  
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;  
  
  @Column
  private String text = "";
  
  @Column
  private boolean done = false;

  public Todo() {
  }

  public Todo(String text, boolean done) {
    this.text = text;
    this.done = done;
  }

  public Todo(Long id, String text, boolean done) {
    this.id = id;
    this.text = text;
    this.done = done;
  }

  public Long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public boolean isDone() {
    return done;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setDone(boolean done) {
    this.done = done;
  }
}
