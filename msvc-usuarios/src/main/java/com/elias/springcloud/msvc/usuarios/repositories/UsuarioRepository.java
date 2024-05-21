package com.elias.springcloud.msvc.usuarios.repositories;

import com.elias.springcloud.msvc.usuarios.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
}
