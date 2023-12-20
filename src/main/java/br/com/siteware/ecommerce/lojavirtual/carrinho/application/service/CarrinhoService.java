package br.com.siteware.ecommerce.lojavirtual.carrinho.application.service;

import br.com.siteware.ecommerce.lojavirtual.carrinho.application.api.*;

import java.util.UUID;

public interface CarrinhoService {
    CarrinhoResponse salvarCarrinho(CarrinhoRequest carrinhoRequest);
    void adicionarItemAoCarrinho(UUID idCarrinho, ItemCarrinhoRequest itemCarrinhoRequest);
    CarrinhoListResponse listarItensDoCarrinho(UUID idCarrinho);
    void alterarCarrinho(UUID idCarrinho, UUID idItemCarrinho, ItemCarrinhoAlterRequest itemCarrinhoAlterRequest);
    void removerItensDoCarrinho(UUID idCarrinho, UUID idItemCarrinho);
}
