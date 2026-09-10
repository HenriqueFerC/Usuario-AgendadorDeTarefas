package br.com.project.agendador_usuario.business.dto.usuarioDto;

import br.com.project.agendador_usuario.business.dto.enderecoDto.CadastroEnderecoDto;
import br.com.project.agendador_usuario.business.dto.telefoneDto.CadastroTelefoneDto;
import br.com.project.agendador_usuario.infrastructure.entity.Endereco;
import br.com.project.agendador_usuario.infrastructure.entity.Telefone;

import java.util.List;

public record CadastroUsuarioDto(String email,
                                 String senha,
                                 String nome,
                                 CadastroEnderecoDto endereco,
                                 List<CadastroTelefoneDto> telefones) {
}
