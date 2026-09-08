package br.com.project.agendador_usuario.business.converter;

import br.com.project.agendador_usuario.business.dto.EnderecoDTO;
import br.com.project.agendador_usuario.business.dto.TelefoneDTO;
import br.com.project.agendador_usuario.business.dto.UsuarioDTO;
import br.com.project.agendador_usuario.infrastructure.entity.Endereco;
import br.com.project.agendador_usuario.infrastructure.entity.Telefone;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .endereco(toEndereco(usuarioDTO.getEndereco()))
                .telefones(toListTelefone(usuarioDTO.getTelefones()))
                .build();
    }


    public Endereco toEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .build();
    }

    public List<Telefone> toListTelefone(List<TelefoneDTO> telefoneDTOList) {
        return telefoneDTOList.stream()
                .map(this::toTelefone)
                .toList();
    }

    public Telefone toTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .endereco(toEnderecoDTO(usuario.getEndereco()))
                .telefones(toListTelefoneDTO(usuario.getTelefones()))
                .build();
    }


    public EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .rua(endereco.getRua())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .build();
    }

    public List<TelefoneDTO> toListTelefoneDTO(List<Telefone> telefoneList) {
        return telefoneList.stream()
                .map(this::toTelefoneDTO)
                .toList();
    }

    public TelefoneDTO toTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }
}
