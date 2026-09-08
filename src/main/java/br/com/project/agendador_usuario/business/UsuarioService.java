package br.com.project.agendador_usuario.business;

import br.com.project.agendador_usuario.business.converter.UsuarioConverter;
import br.com.project.agendador_usuario.business.dto.UsuarioDTO;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;
import br.com.project.agendador_usuario.infrastructure.exceptions.ConflictException;
import br.com.project.agendador_usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        System.out.println(usuarioDTO.getEmail());
        emailExists(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.toUsuario(usuarioDTO);
        return usuarioConverter.toUsuarioDTO(usuarioRepository.save(usuario));
    }

    private void emailExists(String email) {
        try {
            if (usuarioRepository.existsByEmail(email)) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado ", e.getCause());
        }
    }
}
