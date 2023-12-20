package br.com.siteware.ecommerce.lojavirtual.cliente.application.api;

import br.com.siteware.ecommerce.lojavirtual.cliente.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ClienteRestController implements ClienteAPI {
    private final ClienteService clienteService;
    @Override
    public ClienteResponse criarCliente(ClienteRequest clienteRequest) {
        log.info("[inicia] ClienteRestController - criarCliente");
        ClienteResponse cliente = clienteService.salvarCliente(clienteRequest);
        log.info("[finaliza] ClienteRestController - criarCliente");
        return cliente;
    }

    @Override
    public List<ClienteListResponse> listarClientes() {
        log.info("[inicia] ClienteRestController - listarClientes");
        List<ClienteListResponse> clientes = clienteService.listarTodosClientes();
        log.info("[finaliza] ClienteRestController - listarClientes");
        return clientes;
    }
}
