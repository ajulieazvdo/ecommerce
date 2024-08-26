# Loja Virtual

Este projeto é um protótipo de uma nova funcionalidade para um e-commerce, consistindo basicamente de uma Loja Virtual para diversos produtos.

## Funcionalidades

### Produto:

- **Criar um produto (POST):** Permite a criação de um produto e a adição opcional de promoção.
- **Listar Produtos (GET):** Permite listar todos os produtos.
- **Listar um produto (GET):** Permite listar um produto específico.
- **Alterar um produto (PATCH):** Permite a alteração de um produto.
- **Deletar um produto (DELETE):** Permite a exclusão de um produto.

### Cliente:

- **Criar um cliente (POST):** Possibilita a criação de um cliente.
- **Listar clientes (GET):** Possibilita listar todos os clientes.

### Carrinho:

- **Criar um carrinho (POST):** Possibilita a criação de um carrinho associado a um cliente.
- **Adicionar item ao carrinho (POST):** Possibilita adicionar um produto ao carrinho.
- **Alterar um item do carrinho (PATCH):** Possibilita alterar um produto ou a quantidade no carrinho.
- **Remover um item do carrinho (DELETE):** Possibilita remover um produto do carrinho.
- **Listar todos os itens do carrinho (GET):** Possibilita listar os produtos do carrinho, exibindo o valor total calculado com base nos produtos selecionados, considerando suas respectivas promoções.

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando a linguagem Java na versão 17 com o framework Spring Boot na versão 3. O banco de dados escolhido foi o MySQL.

## Documentação no Swagger

Confira a documentação completa no Swagger: [Documentação Swagger](http://localhost:8080/ecommerce/api/swagger-ui/index.html#/)
