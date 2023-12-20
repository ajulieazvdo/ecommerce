package br.com.siteware.ecommerce.lojavirtual.produto.domain;

import br.com.siteware.ecommerce.lojavirtual.produto.application.api.ProdutoAlteraRequest;
import br.com.siteware.ecommerce.lojavirtual.produto.application.api.ProdutoRequest;
import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "idProduto")
@Entity
@Table(name = "produto", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "preco"})})
public class Produto {
    @Id @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID idProduto;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private PromocaoType promocao;

    public Produto(ProdutoRequest produtoRequest) {
        this.nome = produtoRequest.nome();
        this.preco = produtoRequest.preco();
        this.promocao = produtoRequest.promocao();
    }

    public PromocaoType getPromocaoType() {
        return this.promocao;
    }


    public void alterar(ProdutoAlteraRequest produtoRequest) {
        this.nome = produtoRequest.nome();
        this.preco = produtoRequest.preco();
        this.promocao = produtoRequest.promocao();
    }
}
