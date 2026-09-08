package br.com.project.agendador_usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private String nome;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Telefone> telefones;
}
