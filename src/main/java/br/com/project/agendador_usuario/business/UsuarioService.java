package br.com.project.agendador_usuario.business;

import br.com.project.agendador_usuario.business.converter.UsuarioConverter;
import br.com.project.agendador_usuario.business.dto.usuarioDto.CadastroUsuarioDto;
import br.com.project.agendador_usuario.business.dto.usuarioDto.DetalhesUsuarioDto;
import br.com.project.agendador_usuario.infrastructure.entity.Telefone;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;
import br.com.project.agendador_usuario.infrastructure.exceptions.ConflictException;
import br.com.project.agendador_usuario.infrastructure.repository.UsuarioRepository;
import br.com.project.agendador_usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Usuario salvaUsuario(CadastroUsuarioDto usuarioDTO) {
        emailExists(usuarioDTO.email());
        Usuario usuario = usuarioConverter.toUsuario(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        return usuarioRepository.save(usuario);
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

    public Usuario buscarUsuarioPorEmail(String email) {
        try {
            return usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new ConflictException("Usuário não encontrado: " + email));
        } catch (ConflictException e) {
            throw new ConflictException("Usuário não encontrado: ", e.getCause());
        }
    }

    public Usuario atualizarUsuario(String token, CadastroUsuarioDto usuarioDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario usuario = buscarUsuarioPorEmail(email);
        Usuario usuarioAtualizado = usuarioConverter.atualizarUsuario(usuario ,usuarioDTO);
        return usuarioRepository.save(usuarioAtualizado);
    }
}
