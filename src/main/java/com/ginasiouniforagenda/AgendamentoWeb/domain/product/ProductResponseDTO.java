package com.ginasiouniforagenda.AgendamentoWeb.domain.product;

import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, Integer price, String description) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(),product.getDescription());
    }
}
