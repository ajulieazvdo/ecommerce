package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.ItemCarrinho;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CarrinhoRequest(@NotNull UUID idCliente) {
}
