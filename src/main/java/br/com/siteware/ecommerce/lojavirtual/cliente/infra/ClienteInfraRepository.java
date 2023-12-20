package br.com.siteware.ecommerce.lojavirtual.cliente.infra;

import br.com.siteware.ecommerce.lojavirtual.cliente.application.repository.ClienteRepository;
import br.com.siteware.ecommerce.lojavirtual.cliente.domain.Cliente;
import br.com.siteware.ecommerce.lojavirtual.handler.APIException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ClienteInfraRepository implements ClienteRepository {
    private final ClienteSpringDataJpaRepository clienteSpringDataJpaRepository;
    @Override
    public Cliente salvarCliente(Cliente cliente) {
        log.info("[inicia] ClienteInfraRepository - salvarCliente");
        try {
            clienteSpringDataJpaRepository.save(cliente);
        } catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Existem dados duplicados", e);
        }
        log.info("[finaliza] ClienteInfraRepository - salvarCliente");
        return cliente;
    }

    @Override
    public List<Cliente> buscarTodosClientes() {
        log.info("[inicia] ClienteInfraRepository - buscarTodosClientes");
        List<Cliente> clientes = clienteSpringDataJpaRepository.findAll();
        log.info("[finaliza] ClienteInfraRepository - buscarTodosClientes");
        return clientes;
    }
}
