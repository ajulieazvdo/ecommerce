package br.com.siteware.ecommerce.lojavirtual.promocao.application.service;

import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PromocaoStrategyImpl implements PromocaoStrategy {
    @Override
    public BigDecimal aplicarPromocao(Integer quantity, BigDecimal price, Produto produto) {
        PromocaoType promocaoType = produto.getPromocao();
        if (promocaoType == PromocaoType.LEVE_DOIS_PAGUE_UM) {
            return calcularLeveDoisPagueUmPromocao(quantity, price);
        } else if (promocaoType == PromocaoType.TRES_POR_DEZ) {
            return calcularTresPorDezPromocao(quantity, price);
        } else {
            return calcularSemPromocao(quantity, price);
        }
    }

    private static BigDecimal calcularLeveDoisPagueUmPromocao(Integer quantity, BigDecimal price) {
        if (quantity == 2){
            int conjuntosDeDois = quantity / 2;
            int remainder = quantity % 2;
            return BigDecimal.valueOf((conjuntosDeDois + remainder) * price.doubleValue());
        } return calcularSemPromocao(quantity, price);
    }

    private static BigDecimal calcularTresPorDezPromocao(Integer quantity, BigDecimal price) {
        if (quantity == 3){
            int conjuntosDeTres = quantity / 3;
            int remainder = quantity % 3;
            return BigDecimal.valueOf(conjuntosDeTres * 10L)
                    .add(BigDecimal.valueOf(remainder).multiply(price));
        }
        return calcularSemPromocao(quantity, price);
    }

    private static BigDecimal calcularSemPromocao(Integer quantity, BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}