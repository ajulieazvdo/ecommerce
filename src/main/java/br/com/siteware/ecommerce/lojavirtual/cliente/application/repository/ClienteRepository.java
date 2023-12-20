package br.com.siteware.ecommerce.lojavirtual.cliente.application.repository;

import br.com.siteware.ecommerce.lojavirtual.cliente.domain.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente salvarCliente(Cliente cliente);
    List<Cliente> buscarTodosClientes();
}
