package com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Table(name = "cursos_usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class CursoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id",unique = true)
    private Long usuarioId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoUsuario that = (CursoUsuario) o;
        return Objects.equals(id, that.id) && Objects.equals(usuarioId, that.usuarioId);
    }


}
