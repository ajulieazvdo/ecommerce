package br.com.siteware.ecommerce.lojavirtual.produto.application.repository;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository {
    Produto salvarProduto(Produto produto);
    List<Produto> buscarTodosProdutos();
    Produto buscarUmProdutoPorId(UUID idProduct);
    void deletarUmProduto(Produto produto);
}
