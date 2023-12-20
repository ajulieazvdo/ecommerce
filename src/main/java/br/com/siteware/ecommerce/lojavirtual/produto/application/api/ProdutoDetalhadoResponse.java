package br.com.siteware.ecommerce.lojavirtual.produto.application.api;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoDetalhadoResponse(UUID idProduct, String name, BigDecimal price, PromocaoType promocao) {
    public ProdutoDetalhadoResponse(Produto produto) {
        this(produto.getIdProduto(), produto.getNome(), produto.getPreco(), produto.getPromocao());
    }

}
