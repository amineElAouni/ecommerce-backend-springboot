package com.nexio.ecommerce.service;

import com.nexio.ecommerce.model.Produit;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface ProduitService {

    @Valid
    @NotNull
    Optional<Produit> getDetailProduct(long idProduct);

}
