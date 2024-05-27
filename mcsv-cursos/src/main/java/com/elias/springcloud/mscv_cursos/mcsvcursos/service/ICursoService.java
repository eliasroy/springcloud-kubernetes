package com.elias.springcloud.mscv_cursos.mcsvcursos.service;

import com.elias.springcloud.mscv_cursos.mcsvcursos.models.Usuario;
import com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    void eliminarCursoUsuarioporId(Long id);
    Optional<Curso> porIdconUsuario(Long idCurso);
    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);

}
