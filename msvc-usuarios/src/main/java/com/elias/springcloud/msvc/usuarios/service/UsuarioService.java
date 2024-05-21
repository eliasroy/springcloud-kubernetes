package com.elias.springcloud.msvc.usuarios.service;

import com.elias.springcloud.msvc.usuarios.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);

}
