package com.mascena.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mascena.todo.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

     // Definir o método para buscar todas as tarefas de um usuário
     List<Task> findByUser_Id(long id);

}