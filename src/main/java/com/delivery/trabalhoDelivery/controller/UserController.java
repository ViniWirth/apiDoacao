package com.delivery.trabalhoDelivery.controller;

import com.delivery.trabalhoDelivery.model.User;
import com.delivery.trabalhoDelivery.repositories.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")  // Permite requisições apenas do front-end no localhost:3000
public class UserController {

    @Autowired
    UserRepositorio repositorio;

    @PostMapping("/cadastrar")
    public User cadastrar(@RequestBody User user){
        return this.repositorio.save(user);
    }

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id){
        Optional<User> user = repositorio.findById(id);
        return user.orElse(null);  // Retorna null se o usuário não for encontrado
    }

    @DeleteMapping("/deletar/{id}")
    public List<User> delete(@PathVariable("id") Long id){
        Optional<User> user = repositorio.findById(id);
        if (user.isPresent()) {
            repositorio.delete(user.get());
        }
        return listar();
    }

    @PostMapping("/editar")
    public List<User> editar(@RequestBody User user){
        this.repositorio.save(user);
        return listar();
    }

    @GetMapping("/listar")
    public List<User> listar(){
        List<User> users = new ArrayList<>();
        users.addAll(repositorio.findAll());
        return users;
    }
}
