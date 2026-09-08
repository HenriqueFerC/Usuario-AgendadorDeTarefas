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

    private EnderecoDTO endereco;

    private List<TelefoneDTO> telefones;

}
