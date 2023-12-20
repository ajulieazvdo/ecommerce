package br.com.siteware.ecommerce.lojavirtual.carrinho.infra;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarrinhoSpringJpaRepository extends JpaRepository<Carrinho, UUID> {
}
