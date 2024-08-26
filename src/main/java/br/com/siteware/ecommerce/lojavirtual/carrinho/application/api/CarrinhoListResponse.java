package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoListResponse(List<ItemCarrinhoResponse> itens, BigDecimal total) {
    public CarrinhoListResponse(List<ItemCarrinhoResponse> itens, Carrinho carrinho) {
        this(itens, carrinho.getTotal());
    }
}
