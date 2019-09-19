package com.nexio.ecommerce.service;

import com.nexio.ecommerce.model.Catalogue;
import com.nexio.ecommerce.model.Produit;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface CatalogueService {

    @Valid
    @NotNull
    Optional<Catalogue> getDetailCatalogue(long idCatalog);

    @Valid
    List<Produit> getProduitsByCatalogue(Long idCatalogue);

}
