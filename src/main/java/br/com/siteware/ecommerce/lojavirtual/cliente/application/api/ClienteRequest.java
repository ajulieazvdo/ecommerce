package br.com.siteware.ecommerce.lojavirtual.cliente.application.api;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(@NotBlank String nomeCompleto, @NotBlank String cpf) {
}
