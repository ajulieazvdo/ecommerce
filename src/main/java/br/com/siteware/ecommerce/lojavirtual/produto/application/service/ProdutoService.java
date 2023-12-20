package br.com.siteware.ecommerce.lojavirtual.produto.application.service;

import br.com.siteware.ecommerce.lojavirtual.produto.application.api.*;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    ProdutoResponse salvarProduto(ProdutoRequest produtoRequest);
    List<ProdutoListResponse> buscarTodosProdutos();
    ProdutoDetalhadoResponse buscarUmProdutoPorId(UUID idProduto);
    void deletarUmProdutoPorId(UUID idProduto);
    void alterarUmProduto(UUID idProduto, ProdutoAlteraRequest produtoRequest);
}
