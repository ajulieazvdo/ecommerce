package br.com.siteware.ecommerce.lojavirtual.carrinho.application.api;

import br.com.siteware.ecommerce.lojavirtual.carrinho.application.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CarrinhoRestController implements CarrinhoAPI {
    private final CarrinhoService carrinhoService;

    @Override
    public CarrinhoResponse criarCarrinho(CarrinhoRequest carrinhoRequest) {
        log.info("[inicia] CarrinhoRestController - criarCarrinho");
        CarrinhoResponse carrinho = carrinhoService.salvarCarrinho(carrinhoRequest);
        log.info("[finaliza] CarrinhoRestController - criarCarrinho");
        return carrinho;
    }

    @Override
    public void adicionarItemCarrinho(UUID idCarrinho, ItemCarrinhoRequest itemCarrinhoRequest) {
        log.info("[inicia] CarrinhoRestController - adicionarItemCarrinho");
        carrinhoService.adicionarItemAoCarrinho(idCarrinho, itemCarrinhoRequest);
        log.info("[finaliza] CarrinhoRestController - adicionarItemCarrinho");
    }

    @Override
    public CarrinhoListResponse listarItensDoCarrinho(UUID idCarrinho) {
        log.info("[inicia] CarrinhoRestController - listarItensDoCarrinho");
        CarrinhoListResponse carrinho = carrinhoService.listarItensDoCarrinho(idCarrinho);
        log.info("[finaliza] CarrinhoRestController - listarItensDoCarrinho");
        return carrinho;
    }

    @Override
    public void alterarItensDoCarrinho(UUID idCarrinho, UUID idItemCarrinho, ItemCarrinhoAlterRequest itemCarrinhoAlterRequest) {
        log.info("[inicia] CarrinhoRestController - alterarItensDoCarrinho");
        carrinhoService.alterarCarrinho(idCarrinho, idItemCarrinho, itemCarrinhoAlterRequest);
        log.info("[finaliza] CarrinhoRestController - alterarItensDoCarrinho");
    }

    @Override
    public void removerItensDoCarrinho(UUID idCarrinho, UUID idItemCarrinho) {
        log.info("[inicia] CarrinhoRestController - removerItensDoCarrinho");
        carrinhoService.removerItensDoCarrinho(idCarrinho, idItemCarrinho);
        log.info("[finaliza] CarrinhoRestController - removerItensDoCarrinho");
    }
}
