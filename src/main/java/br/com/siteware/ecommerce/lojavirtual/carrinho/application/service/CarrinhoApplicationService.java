package br.com.siteware.ecommerce.lojavirtual.carrinho.application.service;

import br.com.siteware.ecommerce.lojavirtual.carrinho.application.api.*;
import br.com.siteware.ecommerce.lojavirtual.carrinho.application.repository.CarrinhoRepository;
import br.com.siteware.ecommerce.lojavirtual.carrinho.application.repository.ItemCarrinhoRepository;
import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.ItemCarrinho;
import br.com.siteware.ecommerce.lojavirtual.produto.application.repository.ProdutoRepository;
import br.com.siteware.ecommerce.lojavirtual.promocao.application.service.PromocaoStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class CarrinhoApplicationService implements CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final PromocaoStrategy promocaoStrategy;
    private final ItemCarrinhoRepository itemCarrinhoRepository;

    @Override
    public CarrinhoResponse salvarCarrinho(CarrinhoRequest carrinhoRequest) {
        log.info("[inicia] CarrinhoApplicationService - salvarCarrinho");
        var carrinho = new Carrinho(carrinhoRequest);
        carrinhoRepository.salvarCarrinho(carrinho);
        log.info("[finaliza] CarrinhoApplicationService - salvarCarrinho");
        return new CarrinhoResponse(carrinho);
    }

    @Override
    public void adicionarItemAoCarrinho(UUID idCarrinho, ItemCarrinhoRequest itemCarrinhoRequest) {
        log.info("[inicia] CarrinhoApplicationService - adicionarItemAoCarrinho");
        var produto = produtoRepository.buscarUmProdutoPorId(itemCarrinhoRequest.idProduto());
        var carrinho = carrinhoRepository.buscarCarrinhoPorId(idCarrinho);
        carrinho.adicionarProdutoAoCarrinho(produto, itemCarrinhoRequest.quantidade(), promocaoStrategy);
        carrinhoRepository.salvarCarrinho(carrinho);
        log.info("[finaliza] CarrinhoApplicationService - adicionarItemAoCarrinho");
    }

    @Override
    public CarrinhoListResponse listarItensDoCarrinho(UUID idCarrinho) {
        log.info("[inicia] CarrinhoApplicationService - listarItensDoCarrinho");
        Carrinho carrinho = carrinhoRepository.buscarCarrinhoPorId(idCarrinho);
        List<ItemCarrinho> itensDoCarrinho = itemCarrinhoRepository.acharCarrinho(carrinho);
        BigDecimal totalDoCarrinho = carrinho.calcularTotalDosItens(itensDoCarrinho);
        List<ItemCarrinhoResponse> listaItensDoCarrinho = ItemCarrinhoResponse.converte(itensDoCarrinho);
        log.info("[finaliza] CarrinhoApplicationService - listarItensDoCarrinho");
        return new CarrinhoListResponse(listaItensDoCarrinho, totalDoCarrinho);
    }

    @Override
    public void alterarCarrinho(UUID idCarrinho, UUID idItemCarrinho, ItemCarrinhoAlterRequest itemCarrinhoAlterRequest) {
        log.info("[inicia] CarrinhoApplicationService - listarItensDoCarrinho");
        var carrinho = carrinhoRepository.buscarCarrinhoPorId(idCarrinho);
        var itemCarrinho = itemCarrinhoRepository.buscarItemCarrinhoPeloId(idItemCarrinho);
        carrinho.atualizarQuantidadeItemExistente(itemCarrinho, itemCarrinhoAlterRequest.quantidade());
        carrinhoRepository.salvarCarrinho(carrinho);
        log.info("[finaliza] CarrinhoApplicationService - listarItensDoCarrinho");
    }
    @Transactional
    @Override
    public void removerItensDoCarrinho(UUID idCarrinho, UUID idItemCarrinho) {
        log.info("[inicia] CarrinhoApplicationService - removerItensDoCarrinho");
        var carrinho = carrinhoRepository.buscarCarrinhoPorId(idCarrinho);
        var itemCarrinho = itemCarrinhoRepository.buscarItemCarrinhoPeloId(idItemCarrinho);
        carrinho.removerItemDoCarrinho(itemCarrinho);
        itemCarrinhoRepository.deletarItemCarrinho(itemCarrinho);
        carrinho.recalcularTotal();
        log.info("[finaliza] CarrinhoApplicationService - removerItensDoCarrinho");
    }
}
