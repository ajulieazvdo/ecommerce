package br.com.siteware.ecommerce.lojavirtual.carrinho.domain;

import br.com.siteware.ecommerce.lojavirtual.carrinho.application.api.ItemCarrinhoRequest;
import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(AccessLevel.PROTECTED)
@Log4j2
public class ItemCarrinho {
    @Id @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID idItemCarrinho;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private PromocaoType promocao;
    private BigDecimal precoTotalLiquido;
    private BigDecimal precoBruto;
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    public ItemCarrinho(Carrinho carrinho, Produto produto, PromocaoType promocaoProduto,
                        int quantidade, BigDecimal precoComPromocao, BigDecimal precoBrutoSemPromocao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoTotalLiquido = precoComPromocao;
        this.precoBruto = precoBrutoSemPromocao;
        this.carrinho = carrinho;
        this.promocao = promocaoProduto;
    }
}