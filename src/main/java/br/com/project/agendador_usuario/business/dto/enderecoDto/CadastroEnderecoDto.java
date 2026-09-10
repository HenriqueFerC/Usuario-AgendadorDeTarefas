package br.com.project.agendador_usuario.business.dto.enderecoDto;

public record CadastroEnderecoDto(String rua,
                                  String cidade,
                                  String estado,
                                  String cep,
                                  int numero) {
}
