package br.com.siteware.ecommerce.lojavirtual.cliente.application.api;

import br.com.siteware.ecommerce.lojavirtual.cliente.domain.Cliente;

import java.util.UUID;

public record ClienteResponse(UUID idCliente) {
    public ClienteResponse(Cliente cliente) {
        this(cliente.getIdCliente());
    }
}
