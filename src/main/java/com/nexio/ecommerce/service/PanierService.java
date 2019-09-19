package com.nexio.ecommerce.service;

import com.nexio.ecommerce.model.ArticlePanier;
import com.nexio.ecommerce.model.Produit;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;

@Validated
public interface PanierService {

    void addProduct(@Valid Produit product, int quantite);

    void removeProduct(Long idProduct);

    Collection<ArticlePanier> getProductsInCart();
}
