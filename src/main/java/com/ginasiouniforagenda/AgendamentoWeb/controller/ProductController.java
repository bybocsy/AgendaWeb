package com.ginasiouniforagenda.AgendamentoWeb.controller;

import com.ginasiouniforagenda.AgendamentoWeb.domain.product.Product;
import com.ginasiouniforagenda.AgendamentoWeb.domain.product.ProductRequestDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.product.ProductResponseDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.user.User;
import com.ginasiouniforagenda.AgendamentoWeb.repository.ProductRepository;
import com.ginasiouniforagenda.AgendamentoWeb.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/cadastro")
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body) {
        Product newProduct = new Product(body);

        this.productRepository.save(newProduct);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("id") String id){
        Product productBanco = productRepository.findById(id).orElseThrow();

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(productBanco);
        return ResponseEntity.ok(productResponseDTO);
    }


}
