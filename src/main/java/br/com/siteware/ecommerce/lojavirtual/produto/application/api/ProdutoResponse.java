package br.com.siteware.ecommerce.lojavirtual.produto.application.api;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;

import java.util.UUID;

public record ProdutoResponse(UUID idProduto) {
    public ProdutoResponse(Produto produto) {
        this(produto.getIdProduto());
    }
}
