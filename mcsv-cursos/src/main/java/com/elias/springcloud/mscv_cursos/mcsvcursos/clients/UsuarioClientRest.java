package com.elias.springcloud.mscv_cursos.mcsvcursos.clients;

import com.elias.springcloud.mscv_cursos.mcsvcursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-usuarios",url = "localhost:8001")
public interface UsuarioClientRest {

    @GetMapping("/usuarios/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/usuario")
    Usuario crear(@RequestBody Usuario usuario);
}