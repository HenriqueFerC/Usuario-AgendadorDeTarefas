package br.com.project.agendador_usuario.business.dto.usuarioDto;

import br.com.project.agendador_usuario.business.dto.enderecoDto.DetalhesEnderecoDto;
import br.com.project.agendador_usuario.business.dto.telefoneDto.DetalhesTelefoneDto;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;

import java.util.List;

public record DetalhesUsuarioDto(String email, String nome, DetalhesEnderecoDto endereco, List<DetalhesTelefoneDto> telefones) {
    public DetalhesUsuarioDto(Usuario usuario) {
        this(usuario.getEmail(), usuario.getNome(), new DetalhesEnderecoDto(usuario.getEndereco()), usuario.getTelefones().stream().map(DetalhesTelefoneDto::new).toList());
    }
}
