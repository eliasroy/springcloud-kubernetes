package com.elias.springcloud.msvc.usuarios.service;

import com.elias.springcloud.msvc.usuarios.cllients.CursoClientRest;
import com.elias.springcloud.msvc.usuarios.model.entity.Usuario;
import com.elias.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    private final  UsuarioRepository usuarioRepository;
    private final CursoClientRest  clientRest;
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> porId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        clientRest.eliminarCursoUsuarioPorId(id);
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarPorIds(Iterable<Long> ids) {
        return (List<Usuario>) usuarioRepository.findAllById(ids);
    }
}
