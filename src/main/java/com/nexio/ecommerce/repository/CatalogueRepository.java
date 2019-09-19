package com.nexio.ecommerce.repository;

import com.nexio.ecommerce.model.Catalogue;
import com.nexio.ecommerce.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {

    @Query("SELECT c.produits FROM Catalogue c where c.id = ?1")
    List<Produit> getProduitsByCatalogue(Long idCatalogue);

}
