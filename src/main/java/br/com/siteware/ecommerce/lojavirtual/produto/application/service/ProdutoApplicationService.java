package br.com.siteware.ecommerce.lojavirtual.produto.application.service;

import br.com.siteware.ecommerce.lojavirtual.produto.application.api.*;
import br.com.siteware.ecommerce.lojavirtual.produto.application.repository.ProdutoRepository;
import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProdutoApplicationService implements ProdutoService {
    private final ProdutoRepository produtoRepository;
    @Override
    public ProdutoResponse salvarProduto(ProdutoRequest produtoRequest) {
        log.info("[inicia] ProdutoApplicationService - salvarProduto");
        Produto produto = produtoRepository.salvarProduto(new Produto(produtoRequest));
        log.info("[finaliza] ProdutoApplicationService - salvarProduto");
        return new ProdutoResponse(produto);
    }

    @Override
    public List<ProdutoListResponse> buscarTodosProdutos() {
        log.info("[inicia] ProdutoApplicationService - buscarTodosProdutos");
        List<Produto> produtos = produtoRepository.buscarTodosProdutos();
        log.info("[finaliza] ProdutoApplicationService - buscarTodosProdutos");
        return ProdutoListResponse.converte(produtos);
    }

    @Override
    public ProdutoDetalhadoResponse buscarUmProdutoPorId(UUID idProduto) {
        log.info("[inicia] ProdutoApplicationService - buscarUmProdutoPorId");
        Produto produto = produtoRepository.buscarUmProdutoPorId(idProduto);
        log.info("[finaliza] ProdutoApplicationService - buscarUmProdutoPorId");
        return new ProdutoDetalhadoResponse(produto);
    }

    @Override
    public void deletarUmProdutoPorId(UUID idProduto) {
        log.info("[inicia] ProdutoApplicationService - deletarUmProdutoPorId");
        Produto produto = produtoRepository.buscarUmProdutoPorId(idProduto);
        produtoRepository.deletarUmProduto(produto);
        log.info("[finaliza] ProdutoApplicationService - deletarUmProdutoPorId");
    }

    @Override
    public void alterarUmProduto(UUID idProduto, ProdutoAlteraRequest produtoRequest) {
        log.info("[inicia] ProdutoApplicationService - alterarUmProduto");
        Produto produto = produtoRepository.buscarUmProdutoPorId(idProduto);
        produto.alterar(produtoRequest);
        produtoRepository.salvarProduto(produto);
        log.info("[finaliza] ProdutoApplicationService - alterarUmProduto");
    }
}
