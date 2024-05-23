package com.elias.springcloud.mscv_cursos.mcsvcursos.service;

import com.elias.springcloud.mscv_cursos.mcsvcursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
}
