package br.com.siteware.ecommerce.lojavirtual.carrinho.infra;

import br.com.siteware.ecommerce.lojavirtual.carrinho.application.repository.ItemCarrinhoRepository;
import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.ItemCarrinho;
import br.com.siteware.ecommerce.lojavirtual.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Log4j2
@Repository
@RequiredArgsConstructor
public class ItemCarrinhoInfraRepository implements ItemCarrinhoRepository {
    private final ItemCarrinhoSpringJpaRepository itemCarrinhoSpringJpaRepository;
    @Override
    public List<ItemCarrinho> acharCarrinho(Carrinho carrinho) {
        log.info("[inicia] ItemCarrinhoInfraRepository - acharCarrinho");
        List<ItemCarrinho> carrinhoItem = itemCarrinhoSpringJpaRepository.findByCarrinho(carrinho);
        log.info("[finaliz] ItemCarrinhoInfraRepository - acharCarrinho");
        return carrinhoItem;
    }

    @Override
    public ItemCarrinho buscarItemCarrinhoPeloId(UUID idItemCarrinho) {
        log.info("[inicia] ItemCarrinhoInfraRepository - buscarItemCarrinhoPeloId");
        ItemCarrinho itemCarrinhoEncontrado = itemCarrinhoSpringJpaRepository.findById(idItemCarrinho)
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Item Carrinho não encontrado!"));
        log.info("[finaliza] ItemCarrinhoInfraRepository - buscarItemCarrinhoPeloId");
        return itemCarrinhoEncontrado;
    }

    @Override
    public void deletarItemCarrinho(ItemCarrinho itemCarrinho) {
        log.info("[inicia] ItemCarrinhoInfraRepository - deletarItemCarrinho");
        itemCarrinhoSpringJpaRepository.delete(itemCarrinho);
        log.info("[finalzia] ItemCarrinhoInfraRepository - deletarItemCarrinho");
    }
}
