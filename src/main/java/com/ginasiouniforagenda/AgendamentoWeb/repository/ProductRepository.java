package com.ginasiouniforagenda.AgendamentoWeb.repository;

import com.ginasiouniforagenda.AgendamentoWeb.domain.product.Product;
import com.ginasiouniforagenda.AgendamentoWeb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByNameContainingIgnoreCase(String name);
}

