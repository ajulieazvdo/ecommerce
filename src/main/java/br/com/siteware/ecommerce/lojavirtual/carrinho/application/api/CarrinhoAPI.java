package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/carrinho")
@Tag(name = "Carrinho de Compras", description = "Operações relacionadas ao carrinho de compras")
public interface CarrinhoAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Criar Carrinho", description = "Cria um novo carrinho de compras")
    CarrinhoResponse criarCarrinho(@RequestBody @Valid CarrinhoRequest carrinhoRequest);

    @PostMapping(path = "/{idCarrinho}/item")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Adicionar Item Carrinho", description = "Adiciona um novo item ao carrinho")
    void adicionarItemCarrinho(@PathVariable UUID idCarrinho, @RequestBody @Valid ItemCarrinhoRequest itemCarrinhoRequest);

    @GetMapping(path = "/{idCarrinho}/item")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Lista Item Carrinho", description = "Obtém a lista de itens no carrinho e calculo de subtotal e total")
    CarrinhoListResponse listarItensDoCarrinho(@PathVariable UUID idCarrinho);

    @PatchMapping(path = "/{idCarrinho}/item/{idItemCarrinho}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Altera Item Carrinho", description = "Atualiza a quantidade de um item no carrinho")
    void alterarItensDoCarrinho(@PathVariable UUID idCarrinho, @PathVariable UUID idItemCarrinho,
    @RequestBody @Valid ItemCarrinhoAlterRequest itemCarrinhoAlterRequest);

    @DeleteMapping(path = "/{idCarrinho}/item/{idItemCarrinho}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um Item Carrinho", description = "Remove um item do carrinho")
    void removerItensDoCarrinho(@PathVariable UUID idCarrinho, @PathVariable UUID idItemCarrinho);
}