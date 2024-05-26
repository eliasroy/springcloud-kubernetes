package com.elias.springcloud.mscv_cursos.mcsvcursos.models.entity;

import com.elias.springcloud.mscv_cursos.mcsvcursos.models.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Data
@Builder
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //crear la llave foranea
    @JoinColumn(name = "id_cursos")
    private List<CursoUsuario> cursoUsuarios;

    @Transient
    private List<Usuario>usuarios;

    public Curso() {
        this.cursoUsuarios = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    //metodos
    public void addCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
    }
    public void removeCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.remove(cursoUsuario);
    }

}
