package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import java.util.UUID;

public record ItemCarrinhoAlterRequest(UUID idProduto, Integer quantidade) {
}

