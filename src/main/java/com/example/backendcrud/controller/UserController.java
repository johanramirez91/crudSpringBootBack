package com.example.backendcrud.controller;

import com.example.backendcrud.Exception.ResourceNotFoundException;
import com.example.backendcrud.model.User;
import com.example.backendcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
Clase para controlar las operaciones del crud,
se añaden las mejoras de actualizar y crear
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/users") //ruta para consultar la información de los usuarios
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //Obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Crear usuario
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    //Obtener usuarios por Id
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User does not exist with id: " + id));
        return ResponseEntity.ok(user);
    }

    //Actualizar usuarios por Id
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails){
        User updateUser = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User does not exist with id: " + id));
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setEmailId(userDetails.getEmailId());
        userRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    //Eliminar usuario por Id
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User does not exist with id: " + id));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
