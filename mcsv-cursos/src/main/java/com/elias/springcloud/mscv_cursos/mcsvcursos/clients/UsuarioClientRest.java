package com.elias.springcloud.mscv_cursos.mcsvcursos.clients;

import com.elias.springcloud.mscv_cursos.mcsvcursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-usuarios",url = "msvc-usuarios:8001")
public interface UsuarioClientRest {

    @GetMapping("/usuario/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/usuario")
    Usuario crear(@RequestBody Usuario usuario);
    @GetMapping("/usuario/usuarios-por-curso")
    Iterable<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
}