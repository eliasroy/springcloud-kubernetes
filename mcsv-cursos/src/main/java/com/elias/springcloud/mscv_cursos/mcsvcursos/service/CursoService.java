package com.elias.springcloud.mscv_cursos.mcsvcursos.service;

import com.elias.springcloud.mscv_cursos.mcsvcursos.clients.UsuarioClientRest;
import com.elias.springcloud.mscv_cursos.mcsvcursos.models.Usuario;
import com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity.Curso;
import com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity.CursoUsuario;
import com.elias.springcloud.mscv_cursos.mcsvcursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class CursoService implements ICursoService{
    @Autowired
    private CursoRepository repository;

    @Autowired
    private UsuarioClientRest client;

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

    @Override
    @Transactional
    public void eliminarCursoUsuarioporId(Long id) {
        repository.eliminarCursoUsuarioPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porIdconUsuario(Long idCurso) {
        Optional<Curso> cursos = repository.findById(idCurso);
        if(cursos.isPresent()){
           if (!cursos.get().getCursoUsuarios().isEmpty()){
               List<Long> ids=cursos.get().getCursoUsuarios().stream().map(cu->cu.getUsuarioId()).toList();
               List<Usuario> usuarioMcsrv= (List<Usuario>) client.obtenerAlumnosPorCurso(ids);
              Curso curso=cursos.get();
              curso.setUsuarios(usuarioMcsrv);
               return Optional.of(curso);
           }
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {

        Optional<Curso> cursos = repository.findById(cursoId);
        if(cursos.isPresent()){
            Usuario usuarioMcsrv=client.detalle(usuario.getId());
            Curso curso=cursos.get();
            CursoUsuario cursoUsuario=new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMcsrv.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);
            return Optional.of(usuarioMcsrv);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursos = repository.findById(cursoId);
        if(cursos.isPresent()){
            Usuario usuarioNuevoMcsrv=client.crear(usuario);
            Curso curso=cursos.get();
            CursoUsuario cursoUsuario=new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMcsrv.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);
            return Optional.of(usuarioNuevoMcsrv);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursos = repository.findById(cursoId);
        if(cursos.isPresent()){
            Usuario usuarioMcsrv=client.detalle(usuario.getId());
            Curso curso=cursos.get();
            CursoUsuario cursoUsuario=new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMcsrv.getId());

            curso.removeCursoUsuario(cursoUsuario);
            repository.save(curso);
            return Optional.of(usuarioMcsrv);
        }
        return Optional.empty();
    }
}
