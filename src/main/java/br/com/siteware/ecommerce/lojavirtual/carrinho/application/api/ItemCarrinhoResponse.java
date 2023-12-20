package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.ItemCarrinho;
import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ItemCarrinhoResponse(
        UUID idItemCarrinho,
        UUID idProduto,
        BigDecimal produtoPreco,
        String produtoNome,
        Integer quantidade,
        PromocaoType promocao,
        BigDecimal subtotal
) {
    public ItemCarrinhoResponse(ItemCarrinho itemCarrinho) {
        this(itemCarrinho.getIdItemCarrinho(), itemCarrinho.getProduto().getIdProduto(), itemCarrinho.getProduto().getPreco(),
                itemCarrinho.getProduto().getNome(), itemCarrinho.getQuantidade(),
                itemCarrinho.getPromocao(), itemCarrinho.getPrecoTotalLiquido());
    }

    public static List<ItemCarrinhoResponse> converte(List<ItemCarrinho> itens) {
        return itens.stream()
                .map(ItemCarrinhoResponse::new)
                .collect(Collectors.toList());
    }
}


