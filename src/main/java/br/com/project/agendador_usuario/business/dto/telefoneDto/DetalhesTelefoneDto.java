package br.com.project.agendador_usuario.business.dto.telefoneDto;

import br.com.project.agendador_usuario.infrastructure.entity.Telefone;

public record DetalhesTelefoneDto(int ddd, int numero) {
    public DetalhesTelefoneDto(Telefone telefone) {
        this(telefone.getDdd(), telefone.getNumero());
    }
}
