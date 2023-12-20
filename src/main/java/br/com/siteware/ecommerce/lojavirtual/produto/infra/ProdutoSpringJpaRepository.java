package br.com.siteware.ecommerce.lojavirtual.produto.infra;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoSpringJpaRepository extends JpaRepository<Produto, UUID> {
}
