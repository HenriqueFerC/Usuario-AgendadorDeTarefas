package br.com.project.agendador_usuario.infrastructure.repository;

import br.com.project.agendador_usuario.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
