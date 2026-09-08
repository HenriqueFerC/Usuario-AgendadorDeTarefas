package br.com.project.agendador_usuario.business;

import br.com.project.agendador_usuario.business.converter.UsuarioConverter;
import br.com.project.agendador_usuario.business.dto.UsuarioDTO;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;
import br.com.project.agendador_usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.toUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return usuarioConverter.toUsuarioDTO(usuario);
    }
}
