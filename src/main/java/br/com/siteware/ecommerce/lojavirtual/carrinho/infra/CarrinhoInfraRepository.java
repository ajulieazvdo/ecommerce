package br.com.siteware.ecommerce.lojavirtual.carrinho.infra;

import br.com.siteware.ecommerce.lojavirtual.carrinho.application.repository.CarrinhoRepository;
import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import br.com.siteware.ecommerce.lojavirtual.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class CarrinhoInfraRepository implements CarrinhoRepository {
    private final CarrinhoSpringJpaRepository carrinhoSpringJpaRepository;
    @Override
    public Carrinho salvarCarrinho(Carrinho carrinho) {
        log.info("[inicia] CarrinhoInfraRepository - salvarCarrinho");
        try {
            carrinhoSpringJpaRepository.save(carrinho);
        } catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Existem dados duplicados", e);
        }
        log.info("[finaliza] CarrinhoInfraRepository - salvarCarrinho");
        return carrinho;
    }

    @Override
    public Carrinho buscarCarrinhoPorId(UUID idCarrinho) {
        log.info("[inicia] CarrinhoInfraRepository - buscarCarrinhoPorId");
        Carrinho carrinho = carrinhoSpringJpaRepository.findById(idCarrinho)
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Carrinho não encontrado!"));
        log.info("[finaliza] CarrinhoInfraRepository - buscarCarrinhoPorId");
        return carrinho;
    }
}
