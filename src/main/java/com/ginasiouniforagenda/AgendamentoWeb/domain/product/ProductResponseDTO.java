package com.ginasiouniforagenda.AgendamentoWeb.domain.product;

public record ProductResponseDTO(String id, String name, Integer price, String description) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(),product.getDescription());
    }
}
