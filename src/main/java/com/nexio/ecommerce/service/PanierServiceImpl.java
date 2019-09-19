package com.nexio.ecommerce.service;

import com.nexio.ecommerce.model.ArticlePanier;
import com.nexio.ecommerce.model.Produit;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PanierServiceImpl implements PanierService {

    @Getter
    private Map<Long, ArticlePanier> articles = new HashMap<>();

    @Override
    public void addProduct(Produit product, int quantite) {
        ArticlePanier articlePanier = articles.get(product.getId());
        if (articlePanier != null) {
            articlePanier.setQuantite(articlePanier.getQuantite() + quantite);
        } else {
            articles.put(product.getId(), new ArticlePanier(product, quantite));
        }
    }

    @Override
    public void removeProduct(Long idProduct) {
        articles.remove(idProduct);

    }

    @Override
    public Collection<ArticlePanier> getProductsInCart() {
        return articles.values();
    }
}
