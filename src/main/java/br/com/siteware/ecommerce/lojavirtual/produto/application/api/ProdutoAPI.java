package br.com.siteware.ecommerce.lojavirtual.produto.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/produto")
@Tag(name = "Registro de Produto", description = "Operações relacionadas ao registro de produtos")
public interface ProdutoAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Criar produto", description = "Adicionar Novo Produto")
    ProdutoResponse criarProduto(@RequestBody @Valid ProdutoRequest produtoRequest);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Produtos", description = "Listar todos produtos")
    List<ProdutoListResponse> listarTodosProdutos();

    @GetMapping(path = "/{idProduto}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Um Produto", description = "Listar Um Produto por Id")
    ProdutoDetalhadoResponse listarUmProdutoPorId(@PathVariable UUID idProduto);

    @DeleteMapping(path = "/{idProduto}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Um Produto", description = "Deletar Um Produto por Id")
    void deletarUmProdutoPorId(@PathVariable UUID idProduto);

    @PatchMapping(path = "/{idProduto}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Alterar Um Produto", description = "Alterar Um Produto passando o Id")
    void alterarUmProduto(@PathVariable UUID idProduto, @RequestBody @Valid ProdutoAlteraRequest produtoRequest);
}
