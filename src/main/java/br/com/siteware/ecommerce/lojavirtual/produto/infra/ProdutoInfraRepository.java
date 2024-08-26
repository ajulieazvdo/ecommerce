package br.com.siteware.ecommerce.lojavirtual.produto.infra;

import br.com.siteware.ecommerce.lojavirtual.handler.APIException;
import br.com.siteware.ecommerce.lojavirtual.produto.application.repository.ProdutoRepository;
import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ProdutoInfraRepository implements ProdutoRepository {
    private final ProdutoSpringJpaRepository produtoSpringJpaRepository;

    @Override
    public Produto salvarProduto(Produto produto) {
        log.info("[inicia] ProdutoInfraRepository - salvarProduto");
        try {
            produtoSpringJpaRepository.save(produto);
        } catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Existem dados duplicados", e);
        }
        log.info("[finaliza] ProdutoInfraRepository - salvarProduto");
        return produto;
    }

    @Override
    public List<Produto> buscarTodosProdutos() {
        log.info("[inicia] ProdutoInfraRepository - buscarTodosProdutos");
        List<Produto> produtos = produtoSpringJpaRepository.findAll();
        log.info("[finaliza] ProdutoInfraRepository - buscarTodosProdutos");
        return produtos;
    }

    @Override
    public Produto buscarUmProdutoPorId(UUID idProduct) {
        log.info("[inicia] ProdutoInfraRepository - buscarUmProdutoPorId");
        Produto produto = produtoSpringJpaRepository.findById(idProduct)
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
        log.info("[finaliza] ProdutoInfraRepository - buscarUmProdutoPorId");
        return produto;
    }

    @Override
    public void deletarUmProduto(Produto produto) {
        log.info("[inicia] ProdutoInfraRepository - deletarUmProduto");
        produtoSpringJpaRepository.delete(produto);
        log.info("[finaliza] ProdutoInfraRepository - deletarUmProduto");
    }
}
