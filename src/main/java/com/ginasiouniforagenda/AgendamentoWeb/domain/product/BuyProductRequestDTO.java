package com.ginasiouniforagenda.AgendamentoWeb.domain.product;

import java.util.UUID;

public record BuyProductRequestDTO(
        UUID id,
        int qtyPurchased
) {
}
