package com.mascena.todo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mascena.todo.models.User;
import com.mascena.todo.models.User.CreateUser;
import com.mascena.todo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

     @Autowired
     private UserService userService;

     @GetMapping("/{id}")
     public ResponseEntity<User> findById(@PathVariable long id) {
          User user = userService.findById(id);

          return ResponseEntity.ok().body(user);
     }

     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
     @JsonIgnoreProperties(ignoreUnknown = true)
     public ResponseEntity<Void> create(@Valid @RequestBody User u) {
          this.userService.createUser(u);

          URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(u.getId())
                    .toUri();

          return ResponseEntity.created(location).build();
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> delete(@PathVariable long id) {
          this.userService.deleteUser(id);

          return ResponseEntity.noContent().build();
     }

     @PutMapping("/{id}")
     @Validated(CreateUser.class)
     public ResponseEntity<Void> update(@Valid @RequestBody User u, @PathVariable long id) {
          u.setId(id);
          this.userService.updateUser(u);

          return ResponseEntity.noContent().build();
     }

}
