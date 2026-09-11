package br.com.project.agendador_usuario.business.converter;

import br.com.project.agendador_usuario.business.dto.enderecoDto.CadastroEnderecoDto;
import br.com.project.agendador_usuario.business.dto.telefoneDto.CadastroTelefoneDto;
import br.com.project.agendador_usuario.business.dto.usuarioDto.CadastroUsuarioDto;
import br.com.project.agendador_usuario.infrastructure.entity.Endereco;
import br.com.project.agendador_usuario.infrastructure.entity.Telefone;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioConverter {

    private final PasswordEncoder passwordEncoder;

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

    public Usuario atualizarUsuario(Usuario usuario, CadastroUsuarioDto usuarioDTO) {
        return Usuario.builder()
                .id(usuario.getId())
                .nome(usuarioDTO.nome() != null ? usuarioDTO.nome() : usuario.getNome())
                .email(usuarioDTO.email() != null ? usuarioDTO.email() : usuario.getEmail())
                .senha(usuarioDTO.senha() != null ? passwordEncoder.encode(usuarioDTO.senha()) : usuario.getSenha())
                .endereco(usuario.getEndereco())
                .telefones(usuario.getTelefones())
                .build();
    }
}
