package br.com.siteware.ecommerce.lojavirtual.carrinho.application.repository;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.ItemCarrinho;

import java.util.List;
import java.util.UUID;

public interface ItemCarrinhoRepository {
    List<ItemCarrinho> acharCarrinho(Carrinho carrinho);
    ItemCarrinho buscarItemCarrinhoPeloId(UUID idItemCarrinho);
    void deletarItemCarrinho(ItemCarrinho itemCarrinho);
}
