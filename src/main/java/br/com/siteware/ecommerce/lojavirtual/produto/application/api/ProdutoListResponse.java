package br.com.siteware.ecommerce.lojavirtual.produto.application.api;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ProdutoListResponse(UUID idProduct, String name, BigDecimal price, PromocaoType promocao) {
    public static List<ProdutoListResponse> converte(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoListResponse::new)
                .collect(Collectors.toList());
    }

    public ProdutoListResponse(Produto produto) {
        this(produto.getIdProduto(), produto.getNome(), produto.getPreco(), produto.getPromocao());
    }
}
