package br.com.siteware.ecommerce.lojavirtual.carrinho.domain;

import br.com.siteware.ecommerce.lojavirtual.carrinho.application.api.CarrinhoRequest;
import br.com.siteware.ecommerce.lojavirtual.cliente.domain.Cliente;
import br.com.siteware.ecommerce.lojavirtual.handler.APIException;
import br.com.siteware.ecommerce.lojavirtual.produto.domain.Produto;
import br.com.siteware.ecommerce.lojavirtual.promocao.application.service.PromocaoStrategy;
import br.com.siteware.ecommerce.lojavirtual.promocao.domain.PromocaoType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Carrinho {
    @Id @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID idCarrinho;
    @Setter
    private BigDecimal total;
    private LocalDate dataDaCriacao;
    private LocalDate dataDaAtualizacao;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho(CarrinhoRequest carrinhoRequest) {
        this.dataDaCriacao = LocalDate.now();
        relacionaCarrinhoECliente(carrinhoRequest.idCliente());
    }
    public void relacionaCarrinhoECliente(UUID idCliente){
        if (idCliente != null){
            this.cliente = new Cliente();
            cliente.setIdCliente(idCliente);
        }
    }

    public void recalcularTotal() {
        this.total = calcularTotalDosItens(this.itens);
        this.dataDaAtualizacao = LocalDate.now();
    }
    public BigDecimal calcularTotalDosItens(List<ItemCarrinho> itensDoCarrinho) {
        return itensDoCarrinho.stream()
                .map(ItemCarrinho::getPrecoTotalLiquido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void adicionarProdutoAoCarrinho(Produto produto, int quantidade, PromocaoStrategy promocaoStrategy) {
        Optional<ItemCarrinho> itemExistente = encontrarItemPeloIdProduto(produto.getIdProduto());

        if (itemExistente.isPresent()) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "O produto ja foi adicionado ao carrinho!");
        } else {
            adicionarNovoItemCarrinho(produto, quantidade, promocaoStrategy);
        }
        recalcularTotal();
    }

    private Optional<ItemCarrinho> encontrarItemPeloIdProduto(UUID idProduto) {
        return itens.stream().filter(item -> {
                    Produto produto = item.getProduto();
                    return produto != null && produto.getIdProduto().equals(idProduto);
                }).findFirst();
    }

    private void adicionarNovoItemCarrinho(Produto produto, int quantidade, PromocaoStrategy promocaoStrategy) {
        PromocaoStrategy promoStrategy =
                (promocaoStrategy != null) ? promocaoStrategy : produto.getPromocaoType().getPromocaoStrategy();
        ItemCarrinho novoItem = criarNovoItemCarrinho(produto, quantidade, promoStrategy);
        novoItem.setCarrinho(this);
        itens.add(novoItem);
    }

    private ItemCarrinho criarNovoItemCarrinho(Produto produto, int quantidade, PromocaoStrategy promocaoStrategy) {
        PromocaoType promocaoProduto = produto.getPromocao();
        BigDecimal precoComPromocao = (promocaoStrategy != null)
                ? promocaoStrategy.aplicarPromocao(quantidade, produto.getPreco(), produto)
                : produto.getPreco();
        BigDecimal precoBrutoSemPromocao = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));

        return new ItemCarrinho(
                this, produto, promocaoProduto, quantidade, precoComPromocao, precoBrutoSemPromocao);
    }

    public void atualizarQuantidadeItemExistente(ItemCarrinho itemCarrinhoExistente, int novaQuantidade) {
        validarNovaQuantidade(novaQuantidade);

        itemCarrinhoExistente.setQuantidade(novaQuantidade);

        BigDecimal precoComPromocao = calcularPrecoComPromocao(itemCarrinhoExistente);
        BigDecimal precoBrutoSemPromocao = calcularPrecoBrutoSemPromocao(itemCarrinhoExistente);

        itemCarrinhoExistente.setPrecoTotalLiquido(precoComPromocao);
        itemCarrinhoExistente.setPrecoBruto(precoBrutoSemPromocao);
        recalcularTotal();
    }

    private void validarNovaQuantidade(int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "A quantidade deve ser maior que zero.");
        }
    }

    private BigDecimal calcularPrecoComPromocao(ItemCarrinho itemCarrinho) {
        PromocaoStrategy promocaoStrategy = itemCarrinho.getPromocao().getPromocaoStrategy();
        return promocaoStrategy.aplicarPromocao(
                itemCarrinho.getQuantidade(),
                itemCarrinho.getProduto().getPreco(),
                itemCarrinho.getProduto()
        );
    }

    private BigDecimal calcularPrecoBrutoSemPromocao(ItemCarrinho itemCarrinho) {
        return itemCarrinho.getProduto().getPreco()
                .multiply(BigDecimal.valueOf(itemCarrinho.getQuantidade()));
    }

    public void removerItemDoCarrinho(ItemCarrinho itemCarrinho) {
        boolean removido = itens.remove(itemCarrinho);
        if (!removido) {
            throw APIException.build(HttpStatus.NOT_FOUND, "Item de carrinho não encontrado");
        }
    }

}