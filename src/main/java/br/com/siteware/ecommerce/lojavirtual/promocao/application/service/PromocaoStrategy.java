package br.com.siteware.ecommerce.lojavirtual.promocao.application.service;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;

import java.math.BigDecimal;
public interface PromocaoStrategy {
    BigDecimal aplicarPromocao(Integer quantity, BigDecimal price, Produto produto);
}
