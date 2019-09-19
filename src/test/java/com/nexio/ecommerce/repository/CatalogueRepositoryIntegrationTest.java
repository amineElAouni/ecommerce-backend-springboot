package com.nexio.ecommerce.repository;

import com.nexio.ecommerce.model.Catalogue;
import com.nexio.ecommerce.model.Produit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CatalogueRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Test
    public void whenFindById_thenReturnCatalogue() {

        //given
        Catalogue catalogue = Catalogue.builder()
                .description("Ordinateurs Portables")
                .build();
        entityManager.persist(catalogue);
        entityManager.flush();

        //when
        Catalogue found = catalogueRepository.findById(catalogue.getId()).get();

        //then
        assertThat(found.getId()).isEqualTo(catalogue.getId());
    }

    @Test
    public void whenFindById_thenReturnListOfProducts() {

        //given
        Produit produit = Produit.builder()
                .name("MacBook Pro")
                .description("Très performant")
                .currentPrice(22000)
                .available(true)
                .build();
        List<Produit> produits = new ArrayList<>();
        produits.add(produit);
        Catalogue catalogue = Catalogue.builder()
                .description("Ordinateurs Portables")
                .produits(produits)
                .build();

        produit.setCatalogue(catalogue);
        entityManager.persist(catalogue);
        entityManager.flush();

        //when
        List<Produit> found = catalogueRepository.getProduitsByCatalogue(catalogue.getId());

        //then
        assertThat(found.get(0)).isEqualTo(catalogue.getProduits().get(0));
    }
}
