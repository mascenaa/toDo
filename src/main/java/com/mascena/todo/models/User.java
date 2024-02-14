package com.mascena.todo.models;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
     // Definir grupos de validação
     public interface CreateUser {
     }

     public interface UpdateUser {
     }

     // Definir o nome da tabela no banco de dados para a entidade
     public static final String TABLE_NAME = "usuario";

     @Id
     // Definir a estratégia de geração de chave primária (Auto Incremento no MySQL)
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     // Definir o nome da coluna no banco de dados
     @Column(name = "id", unique = true)
     private Long id;

     // Definir o nome da coluna no banco de dados
     @Column(name = "username", nullable = false, unique = true, length = 100)
     @NotNull(groups = CreateUser.class)
     @NotEmpty(groups = CreateUser.class)
     @Size(min = 3, max = 100, groups = CreateUser.class)
     private String username;

     // Definir o nome da coluna no banco de dados
     @Column(name = "password", nullable = false, length = 30)
     @NotNull(groups = { CreateUser.class, UpdateUser.class })
     @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
     @Size(min = 8, max = 30, groups = { CreateUser.class, UpdateUser.class })
     private String password;

     // Definir o relacionamento entre as entidades User e Task
     @OneToMany(mappedBy = "user")
     @Column(name = "tarefas")
     private List<Task> tarefas = new ArrayList<Task>();

     // Construtores
     public User() {
     }

     public User(String username, String password) {
          this.username = username;
          this.password = password;
     }

     public Long getId() {
          return id;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public List<Task> getTarefas() {
          return tarefas;
     }

     public void setTarefas(List<Task> tarefas) {
          this.tarefas = tarefas;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o)
               return true;
          if (o == null || getClass() != o.getClass())
               return false;

          User user = (User) o;

          return id != null ? id.equals(user.id) : user.id == null;
     }

     @Override
     public int hashCode() {
          return Objects.hash(id, username, password);
     }

     public void setId(Object object) {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'setId'");
     }

}
