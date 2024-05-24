package com.elias.springcloud.mscv_cursos.mcsvcursos.controller;

import com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity.Curso;
import com.elias.springcloud.mscv_cursos.mcsvcursos.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private ICursoService service;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Curso>> listar(){
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Curso>> detalle(Long id){
        Optional<Curso> curso = service.porId(id);
        if(curso.isPresent())
            return ResponseEntity.ok(curso);
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Curso curso){
        return ResponseEntity.ok(service.guardar(curso));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> actualizar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> o = service.porId(id);
        if(o.isPresent()){
            Curso cursobd = o.get();
            cursobd.setNombre(curso.getNombre());
            return ResponseEntity.ok(service.guardar(cursobd));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> o = service.porId(id);
        if(!o.isPresent())
            return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
