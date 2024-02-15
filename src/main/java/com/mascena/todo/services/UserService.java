package com.mascena.todo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mascena.todo.models.User;
import com.mascena.todo.repositories.TaskRepository;
import com.mascena.todo.repositories.UserRepository;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private TaskRepository taskRepository;

     public User findById(long id) {
          Optional<User> user = this.userRepository.findById(id);

          return user.orElseThrow(RuntimeException::new);
     }

     @Transactional
     public User createUser(User user) {
          // Garante que o usuário não tenha um ID (para garantir que seja um novo
          // usuário)
          user.setId(null);
          user = this.userRepository.save(user);
          this.taskRepository.saveAll(user.getTarefas());

          return user;
     }

     @Transactional
     public User updateUser(User user) {
          User newUser = findById(user.getId());
          newUser.setPassword(user.getPassword());

          return this.userRepository.save(newUser);
     }

     @Transactional
     public void deleteUser(long id) {
          findById(id);
          try {
               this.userRepository.deleteById(id);
          } catch (Exception e) {
               throw new RuntimeException("Erro ao deletar usuário, verifique se o usuário possui tarefas associadas.");
          }
     }

}
