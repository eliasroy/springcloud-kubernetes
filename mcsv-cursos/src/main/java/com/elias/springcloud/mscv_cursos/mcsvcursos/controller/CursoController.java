package com.elias.springcloud.mscv_cursos.mcsvcursos.controller;

import com.elias.springcloud.mscv_cursos.mcsvcursos.entity.Curso;
import com.elias.springcloud.mscv_cursos.mcsvcursos.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private ICursoService service;
    @GetMapping
    public ResponseEntity<List<Curso>> listar(){
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curso>> detalle(Long id){
        Optional<Curso> curso = service.porId(id);
        if(curso.isPresent())
            return ResponseEntity.ok(curso);
        return ResponseEntity.notFound().build();
    }
}
