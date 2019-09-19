package com.nexio.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticlePanier {

    private Produit produit;

    private int quantite;
}
