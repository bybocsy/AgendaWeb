package com.ginasiouniforagenda.AgendamentoWeb.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price;

    private String description;

    private String category;

    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
        this.description = data.description();
        this.category = data.category();
    }
}
