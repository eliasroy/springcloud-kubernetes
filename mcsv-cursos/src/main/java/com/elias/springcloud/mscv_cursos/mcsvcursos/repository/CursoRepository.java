package com.elias.springcloud.mscv_cursos.mcsvcursos.repository;

import com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso,Long> {
}
