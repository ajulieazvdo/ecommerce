package br.com.siteware.ecommerce.lojavirtual.cliente.application.service;

import br.com.siteware.ecommerce.lojavirtual.cliente.application.api.ClienteListResponse;
import br.com.siteware.ecommerce.lojavirtual.cliente.application.api.ClienteRequest;
import br.com.siteware.ecommerce.lojavirtual.cliente.application.api.ClienteResponse;
import br.com.siteware.ecommerce.lojavirtual.cliente.application.repository.ClienteRepository;
import br.com.siteware.ecommerce.lojavirtual.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse salvarCliente(ClienteRequest clienteRequest) {
        log.info("[inicia] ClienteApplicationService - salvarCliente");
        Cliente cliente = clienteRepository.salvarCliente(new Cliente(clienteRequest));
        log.info("[finaliza] ClienteApplicationService - salvarCliente");
        return new ClienteResponse(cliente);
    }

    @Override
    public List<ClienteListResponse> listarTodosClientes() {
        log.info("[inicia] ClienteApplicationService - listarTodosClientes");
        List<Cliente> clientes = clienteRepository.buscarTodosClientes();
        log.info("[finaliza] ClienteApplicationService - listarTodosClientes");
        return ClienteListResponse.converte(clientes);
    }
}
