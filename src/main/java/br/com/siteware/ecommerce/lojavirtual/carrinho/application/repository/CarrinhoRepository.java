package br.com.siteware.ecommerce.lojavirtual.carrinho.application.repository;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;

import java.util.UUID;

public interface CarrinhoRepository {
    Carrinho salvarCarrinho(Carrinho carrinho);
    Carrinho buscarCarrinhoPorId(UUID idCarrinho);
}
