package com.nexio.ecommerce.repository;

import com.nexio.ecommerce.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
