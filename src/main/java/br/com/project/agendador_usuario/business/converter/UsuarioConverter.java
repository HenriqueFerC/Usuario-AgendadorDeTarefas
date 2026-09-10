package br.com.project.agendador_usuario.business.converter;

import br.com.project.agendador_usuario.business.dto.enderecoDto.CadastroEnderecoDto;
import br.com.project.agendador_usuario.business.dto.telefoneDto.CadastroTelefoneDto;
import br.com.project.agendador_usuario.business.dto.usuarioDto.CadastroUsuarioDto;
import br.com.project.agendador_usuario.infrastructure.entity.Endereco;
import br.com.project.agendador_usuario.infrastructure.entity.Telefone;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario toUsuario(CadastroUsuarioDto usuarioDTO) {
        Endereco endereco = toEndereco(usuarioDTO.endereco());
        List<Telefone> telefones = toListTelefone(usuarioDTO.telefones());

        Usuario usuario = Usuario.builder()
                .nome(usuarioDTO.nome())
                .email(usuarioDTO.email())
                .senha(usuarioDTO.senha())
                .endereco(endereco)
                .telefones(telefones)
                .build();

        endereco.setUsuario(usuario);
        telefones.forEach(telefone -> telefone.setUsuario(usuario));
        return usuario;
    }


    public Endereco toEndereco(CadastroEnderecoDto enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.rua())
                .cidade(enderecoDTO.cidade())
                .estado(enderecoDTO.estado())
                .cep(enderecoDTO.cep())
                .numero(enderecoDTO.numero())
                .build();
    }

    public List<Telefone> toListTelefone(List<CadastroTelefoneDto> telefoneDTOList) {
        return telefoneDTOList.stream()
                .map(this::toTelefone)
                .toList();
    }

    public Telefone toTelefone(CadastroTelefoneDto telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.ddd())
                .numero(telefoneDTO.numero())
                .build();
    }
}
