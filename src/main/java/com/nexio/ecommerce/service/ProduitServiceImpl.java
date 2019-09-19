package com.nexio.ecommerce.service;

import com.nexio.ecommerce.model.Produit;
import com.nexio.ecommerce.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Optional<Produit> getDetailProduct(long idProduct) {
        return produitRepository.findById(idProduct);
    }

}
