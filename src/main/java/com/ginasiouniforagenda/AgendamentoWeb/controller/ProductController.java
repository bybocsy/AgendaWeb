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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/nome/{name}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByName(@PathVariable("name") String name) {
        List<ProductResponseDTO> productResponseDTOList = productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        if (productResponseDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productResponseDTOList);
    }

    @GetMapping("/categoria/{category}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByCategory(@PathVariable("category") String category) {
        List<ProductResponseDTO> productResponseDTOList = productRepository.findByCategoryContainingIgnoreCase(category)
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        if (productResponseDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productResponseDTOList);
    }

    @GetMapping("/listagem")
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productResponseDTOList = this.productRepository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productResponseDTOList);
    }

}
