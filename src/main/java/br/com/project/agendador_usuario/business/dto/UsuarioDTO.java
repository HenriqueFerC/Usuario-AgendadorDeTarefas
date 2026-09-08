package br.com.project.agendador_usuario.business.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String email;

    private String senha;

    private String nome;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private EnderecoDTO endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<TelefoneDTO> telefones;

}
