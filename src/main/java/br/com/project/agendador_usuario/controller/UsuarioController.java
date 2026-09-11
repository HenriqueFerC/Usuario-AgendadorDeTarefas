package br.com.project.agendador_usuario.controller;

import br.com.project.agendador_usuario.business.UsuarioService;
import br.com.project.agendador_usuario.business.dto.usuarioDto.CadastroUsuarioDto;
import br.com.project.agendador_usuario.business.dto.usuarioDto.DetalhesUsuarioDto;
import br.com.project.agendador_usuario.infrastructure.entity.Usuario;
import br.com.project.agendador_usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    private ResponseEntity<DetalhesUsuarioDto> salvaUsuario(@RequestBody CadastroUsuarioDto usuarioDTO, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.salvaUsuario(usuarioDTO);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDto(usuario));
    }

    @PostMapping("/login")
    private String login(@RequestBody CadastroUsuarioDto usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.email(),
                        usuarioDTO.senha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<DetalhesUsuarioDto> buscarUsuarioPorEmail(@RequestParam("email") String email) {
        Usuario usuarioDTO = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuarioDTO));
    }

    @PutMapping
    public ResponseEntity<DetalhesUsuarioDto> atualizarUsuario(@RequestHeader("Authorization") String token, @RequestBody CadastroUsuarioDto usuarioDTO) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(token, usuarioDTO);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuarioAtualizado));
    }
}
