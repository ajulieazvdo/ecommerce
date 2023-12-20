package br.com.siteware.ecommerce.lojavirtual.produto.application.api;

import br.com.siteware.ecommerce.lojavirtual.produto.application.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ProdutoRestController implements ProdutoAPI {
    private final ProdutoService produtoService;

    @Override
    public ProdutoResponse criarProduto(ProdutoRequest produtoRequest) {
        log.info("[inicia] ProdutoRestController - criarProduto");
        ProdutoResponse produto = produtoService.salvarProduto(produtoRequest);
        log.info("[finaliza] ProdutoRestController - criarProduto");
        return produto;
    }

    @Override
    public List<ProdutoListResponse> listarTodosProdutos() {
        log.info("[inicia] ProdutoRestController - listarTodosProdutos");
        List<ProdutoListResponse> produtos = produtoService.buscarTodosProdutos();
        log.info("[finaliza] ProdutoRestController - listarTodosProdutos");
        return produtos;
    }

    @Override
    public ProdutoDetalhadoResponse listarUmProdutoPorId(UUID idProduto) {
        log.info("[inicia] ProdutoRestController - listarUmProdutoPorId");
        ProdutoDetalhadoResponse produto = produtoService.buscarUmProdutoPorId(idProduto);
        log.info("[finaliza] ProdutoRestController - listarUmProdutoPorId");
        return produto;
    }

    @Override
    public void deletarUmProdutoPorId(UUID idProduto) {
        log.info("[inicia] ProdutoRestController - deletarUmProdutoPorId");
        produtoService.deletarUmProdutoPorId(idProduto);
        log.info("[finaliza] ProdutoRestController - deletarUmProdutoPorId");
    }

    @Override
    public void alterarUmProduto(UUID idProduto, ProdutoAlteraRequest productRequest) {
        log.info("[inicia] ProdutoRestController - alterarUmProduto");
        produtoService.alterarUmProduto(idProduto, productRequest);
        log.info("[finaliza] ProdutoRestController - alterarUmProduto");
    }
}
