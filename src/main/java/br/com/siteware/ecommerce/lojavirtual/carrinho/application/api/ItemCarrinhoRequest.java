package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import java.util.UUID;

public record ItemCarrinhoRequest(UUID idProduto, Integer quantidade) {
    public ItemCarrinhoRequest(UUID idProduto, Integer quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }
}
