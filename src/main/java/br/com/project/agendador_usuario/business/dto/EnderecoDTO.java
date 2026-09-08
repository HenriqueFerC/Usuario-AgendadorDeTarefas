package br.com.project.agendador_usuario.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {

    private String rua;

    private String cidade;

    private String estado;

    private String cep;

    private int numero;
}
