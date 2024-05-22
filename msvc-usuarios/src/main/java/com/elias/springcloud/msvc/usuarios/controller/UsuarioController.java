package com.elias.springcloud.msvc.usuarios.controller;


import com.elias.springcloud.msvc.usuarios.model.entity.Usuario;
import com.elias.springcloud.msvc.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> usuario = service.porId(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }







}
