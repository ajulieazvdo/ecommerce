package br.com.siteware.ecommerce.lojavirtual.promocao.domain;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import br.com.siteware.ecommerce.lojavirtual.promocao.application.service.PromocaoStrategy;
import br.com.siteware.ecommerce.lojavirtual.promocao.application.service.PromocaoStrategyImpl;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
public enum PromocaoType {

    LEVE_DOIS_PAGUE_UM("Leve 2 e Pague 1", new PromocaoStrategyImpl()),
    TRES_POR_DEZ("3 por R$10", new PromocaoStrategyImpl()),
    SEM_PROMOCAO("Produto s/ promocao", new PromocaoStrategyImpl());

    PromocaoType(String descricao, PromocaoStrategy promocaoStrategy) {
        this.descricao = descricao;
        this.promocaoStrategy = promocaoStrategy;
    }

    private String descricao;
    private PromocaoStrategy promocaoStrategy;
    public BigDecimal applicarPromossao(int quantity, BigDecimal price) {
        return promocaoStrategy.aplicarPromocao(quantity, price, new Produto());
    }
}