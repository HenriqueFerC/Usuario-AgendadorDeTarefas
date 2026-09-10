package br.com.project.agendador_usuario.business.dto.enderecoDto;

import br.com.project.agendador_usuario.infrastructure.entity.Endereco;

public record DetalhesEnderecoDto(String rua, String cidade, String estado, String cep, int numero) {
    public DetalhesEnderecoDto(Endereco endereco) {
        this(endereco.getRua(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), endereco.getNumero());
    }
}
