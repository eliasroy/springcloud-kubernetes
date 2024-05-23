package com.elias.springcloud.mscv_cursos.mcsvcursos.service;

import com.elias.springcloud.mscv_cursos.mcsvcursos.entity.Curso;
import com.elias.springcloud.mscv_cursos.mcsvcursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CursoService implements ICursoService{
    @Autowired
    private CursoRepository repository;
    @Override
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    public Optional<Curso> porId(Long id) {
        Optional<Curso> curso = repository.findById(id);
        if(curso.isPresent())
            return curso;
        return Optional.empty();
    }

    @Override
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
