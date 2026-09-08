package br.com.project.agendador_usuario.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDTO {

    private int ddd;

    private int numero;
}
