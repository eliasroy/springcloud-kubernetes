package com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity;

import com.elias.springcloud.mscv_cursos.mcsvcursos.models.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cursos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //crear la llave foranea
    @JoinColumn(name = "usuario_id")
    private List<CursoUsuario> cursoUsuarios;

    @Transient
    private List<Usuario>usuarios;



    //metodos
    public void addCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
    }
    public void removeCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.remove(cursoUsuario);
    }

}
