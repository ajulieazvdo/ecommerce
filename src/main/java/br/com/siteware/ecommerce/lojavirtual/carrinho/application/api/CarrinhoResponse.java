package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CarrinhoResponse(UUID idCarrinho, @JsonIgnore List<ItemCarrinhoResponse> itens, LocalDate dataDaCriacao) {
    public CarrinhoResponse(Carrinho carrinho) {
        this(carrinho.getIdCarrinho(), ItemCarrinhoResponse.converte(carrinho.getItens()), carrinho.getDataDaCriacao());
    }
}
