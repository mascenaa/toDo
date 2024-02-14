package com.mascena.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mascena.todo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     User findById(long id);

     User findByUsername(String username);

}