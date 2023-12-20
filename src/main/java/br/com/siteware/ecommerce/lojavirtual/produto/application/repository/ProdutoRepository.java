package br.com.siteware.ecommerce.lojavirtual.produto.application.repository;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository {
    Produto salvarProduto(Produto produto);
    List<Produto> buscarTodosProdutos();
    Produto buscarUmProdutoPorId(UUID idProduct);
    Optional<Produto> consultaProdutoOptionalAtravesId(UUID idProduto);
    void deletarUmProduto(Produto produto);
}
