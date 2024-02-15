package com.mascena.todo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mascena.todo.models.Task;
import com.mascena.todo.models.User;
import com.mascena.todo.repositories.TaskRepository;

@Service
public class TaskService {

     @Autowired
     private TaskRepository taskRepository;

     @Autowired
     private UserService userService;

     public Task findById(long id) {
          Optional<Task> task = this.taskRepository.findById(id);

          return task.orElseThrow(RuntimeException::new);
     }

     @Transactional
     public Task createTask(Task task) {
          User user = this.userService.findById(task.getUser().getId());
          task.setId(null);
          task.setUser(user);

          task = this.taskRepository.save(task);

          return task;
     }

     @Transactional
     public Task updateTask(Task task) {
          Task newTask = findById(task.getId());

          newTask.setDescricao(task.getDescricao());

          return this.taskRepository.save(newTask);
     }

     @Transactional
     public Task deleteTask(long id) {
          Task task = findById(id);
          try {
               this.taskRepository.deleteById(id);
          } catch (Exception e) {
               throw new RuntimeException("Erro ao deletar tarefa.");
          }
          return task;
     }
}
