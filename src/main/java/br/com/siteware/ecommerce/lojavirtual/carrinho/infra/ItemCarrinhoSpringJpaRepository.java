package br.com.siteware.ecommerce.lojavirtual.carrinho.infra;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemCarrinhoSpringJpaRepository extends JpaRepository<ItemCarrinho, UUID> {
    List<ItemCarrinho> findByCarrinho(Carrinho carrinho);
}
