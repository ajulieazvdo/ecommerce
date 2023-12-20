package br.com.siteware.ecommerce.lojavirtual.cliente.application.service;

import br.com.siteware.ecommerce.lojavirtual.cliente.application.api.ClienteListResponse;
import br.com.siteware.ecommerce.lojavirtual.cliente.application.api.ClienteRequest;
import br.com.siteware.ecommerce.lojavirtual.cliente.application.api.ClienteResponse;

import java.util.List;

public interface ClienteService {
    ClienteResponse salvarCliente(ClienteRequest clienteRequest);
    List<ClienteListResponse> listarTodosClientes();
}
