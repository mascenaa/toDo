package com.mascena.todo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
     public static final String TABLE_NAME = "tarefa";

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", unique = true)
     private Long id;

     @ManyToOne
     @JoinColumn(name = "usuario_id", nullable = false)
     private User user;

     @Column(name = "descricao", nullable = false, length = 150)
     private String descricao;

     public Task() {
     }

     public Task(User user, String descricao) {
          this.user = user;
          this.descricao = descricao;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public User getUser() {
          return user;
     }

     public void setUser(User user) {
          this.user = user;
     }

     public String getDescricao() {
          return descricao;
     }

     public void setDescricao(String descricao) {
          this.descricao = descricao;
     }

     @Override
     public String toString() {
          return "Task [id=" + id + ", user=" + user + ", descricao=" + descricao + "]";
     }
}
