package com.nexio.ecommerce.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nexio.ecommerce.model.AddProductRequest;
import com.nexio.ecommerce.model.ArticlePanier;
import com.nexio.ecommerce.model.Catalogue;
import com.nexio.ecommerce.model.Produit;
import com.nexio.ecommerce.service.CatalogueService;
import com.nexio.ecommerce.service.PanierService;
import com.nexio.ecommerce.service.ProduitService;
import com.nexio.ecommerce.utils.exception.ResourceNotFoundException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Api(value = "Ecommerce Management System", description = "Opérations Rest Test Technique")
@RestController
@RequestMapping("/api/v1")
public class EcommerceController {

    private final ProduitService produitService;

    private final CatalogueService catalogueService;

    private final PanierService panierService;

    @Autowired
    public EcommerceController(ProduitService produitService, CatalogueService catalogueService, PanierService panierService) {
        this.produitService = produitService;
        this.catalogueService = catalogueService;
        this.panierService = panierService;
    }

    @ApiOperation(value = "Détail du produit par son Id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Elément trouvé avec succès"),
            @ApiResponse(code = 404, message = "La ressource demandée n'existe pas")
    })
    @GetMapping("/produits/{id}")
    public ResponseEntity<Produit> getDetailProductById(
            @ApiParam(value = "Id du produit à chercher", required = true) @PathVariable(value = "id") Long idProduct)
            throws ResourceNotFoundException, JsonProcessingException {
        Produit produit = produitService.getDetailProduct(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé pour id : " + idProduct));
        return ResponseEntity.ok().body(produit);
    }

    @ApiOperation(value = "Détail du catalogue par son Id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Elément trouvé avec succès"),
            @ApiResponse(code = 404, message = "La ressource demandée n'existe pas")
    })
    @GetMapping("/catalogues/{id}")
    public ResponseEntity<Catalogue> getDetailCatalogueById(
            @ApiParam(value = "Id du catalogue à chercher", required = true) @PathVariable(value = "id") Long idCatalog)
            throws ResourceNotFoundException {
        Catalogue catalogue = catalogueService.getDetailCatalogue(idCatalog)
                .orElseThrow(() -> new ResourceNotFoundException("Catalogue non trouvé pour id : " + idCatalog));
        return ResponseEntity.ok().body(catalogue);
    }

    @ApiOperation(value = "Liste des produits par catalogue", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produits trouvés avec succès"),
            @ApiResponse(code = 404, message = "Aucun produit n'existe pour ce catalogue")
    })
    @GetMapping("/catalogues/{id}/produits")
    public ResponseEntity<?> getCatalogOfProducts(@PathVariable(value = "id") Long idCatalog)
            throws ResourceNotFoundException {
        Optional<Catalogue> catalogue = catalogueService.getDetailCatalogue(idCatalog);
        if (catalogue.isPresent()) {
            List<Produit> produits = catalogueService.getProduitsByCatalogue(idCatalog);
            return ResponseEntity.ok().body(produits);
        } else {
            throw new ResourceNotFoundException("Pas de produits trouvés pour ce catalogue dont id : " + idCatalog);
        }
    }

    @ApiOperation(value = "Détail du produit par son Id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produit ajouté au panier avec succès"),
            @ApiResponse(code = 404, message = "La ressource demandée n'existe pas")
    })
    @PostMapping("/panier")
    public ResponseEntity<?> addProductToShoppingCart(@RequestBody AddProductRequest addProductRequest)
            throws ResourceNotFoundException {
        Produit produit = produitService.getDetailProduct(addProductRequest.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé pour id : "
                        + addProductRequest.getIdProduct()));
        panierService.addProduct(produit, addProductRequest.getQuantite());
        return ResponseEntity.created(URI.create("api/v1/panier")).build();
    }

    @ApiOperation(value = "Liste des produits du panier")
    @GetMapping("/panier")
    public ResponseEntity<Collection<ArticlePanier>> getProductFromShoppingCart() {
        Collection<ArticlePanier> produits = panierService.getProductsInCart();
        return ResponseEntity.ok().body(produits);
    }

    @ApiOperation(value = "Supprimer un produit du panier")
    @DeleteMapping("/panier/{id}")
    public ResponseEntity<?> deleteProductFromShoppingCart(@PathVariable(value = "id") Long idProduct)
            throws ResourceNotFoundException {
        Produit produit = produitService.getDetailProduct(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé pour id : " + idProduct));
        panierService.removeProduct(produit.getId());
        return new ResponseEntity<>(idProduct, HttpStatus.OK);
    }
}
