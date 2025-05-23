package com.ginasiouniforagenda.AgendamentoWeb.repository;

import com.ginasiouniforagenda.AgendamentoWeb.domain.product.Product;
import com.ginasiouniforagenda.AgendamentoWeb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryContainingIgnoreCase(String category);
}

