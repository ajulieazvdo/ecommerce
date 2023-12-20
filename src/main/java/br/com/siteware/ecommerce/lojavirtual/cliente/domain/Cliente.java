package br.com.siteware.ecommerce.lojavirtual.cliente.domain;

import br.com.siteware.ecommerce.lojavirtual.carrinho.domain.Carrinho;
import br.com.siteware.ecommerce.lojavirtual.cliente.application.api.ClienteRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "idCliente")
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome_completo", "cpf"})})
public class Cliente {
    @Id @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    @Setter
    private UUID idCliente;
    @Column(nullable = false, length = 100, name = "nome_completo")
    private String nomeCompleto;
    @CPF
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    List<Carrinho> carrinhos;
    public Cliente(ClienteRequest clienteRequest) {
        this.nomeCompleto = clienteRequest.nomeCompleto();
        this.cpf = clienteRequest.cpf();
    }
}
