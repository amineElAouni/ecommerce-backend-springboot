package com.nexio.ecommerce.service;

import com.nexio.ecommerce.model.Catalogue;
import com.nexio.ecommerce.model.Produit;
import com.nexio.ecommerce.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogueServiceImpl implements CatalogueService {

    private final CatalogueRepository catalogueRepository;

    @Autowired
    public CatalogueServiceImpl(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @Override
    public Optional<Catalogue> getDetailCatalogue(long idCatalog) {
        return catalogueRepository.findById(idCatalog);
    }

    @Override
    public List<Produit> getProduitsByCatalogue(Long idCatalogue) {
        return catalogueRepository.getProduitsByCatalogue(idCatalogue);
    }
}
