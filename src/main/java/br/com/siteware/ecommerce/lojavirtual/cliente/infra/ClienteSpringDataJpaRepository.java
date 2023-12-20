package br.com.siteware.ecommerce.lojavirtual.cliente.infra;

import br.com.siteware.ecommerce.lojavirtual.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteSpringDataJpaRepository extends JpaRepository<Cliente, UUID> {
}
