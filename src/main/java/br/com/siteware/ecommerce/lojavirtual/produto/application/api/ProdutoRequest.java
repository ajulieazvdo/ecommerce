package br.com.siteware.ecommerce.lojavirtual.produto.application.api;

import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(@NotBlank String nome, @NotNull BigDecimal preco, PromocaoType promocao) {
}
