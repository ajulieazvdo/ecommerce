package br.com.siteware.ecommerce.lojavirtual.cliente.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cliente")
@Tag(name = "Registro de Cliente", description = "Operações relacionadas ao registro de cliente")
public interface ClienteAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Criar cliente", description = "Adicionar Novo Cliente")
    ClienteResponse criarCliente(@RequestBody @Valid ClienteRequest clienteRequest);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Cliente", description = "Listar todos clientes")
    List<ClienteListResponse> listarClientes();

}
