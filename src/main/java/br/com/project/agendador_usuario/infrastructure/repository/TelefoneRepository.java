package br.com.project.agendador_usuario.infrastructure.repository;

import br.com.project.agendador_usuario.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
