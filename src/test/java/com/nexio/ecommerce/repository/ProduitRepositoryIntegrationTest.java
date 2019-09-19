package com.nexio.ecommerce.repository;

import com.nexio.ecommerce.model.Catalogue;
import com.nexio.ecommerce.model.Produit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProduitRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void whenFindById_thenReturnCatalogue() {

        //given
        Catalogue catalogue = Catalogue.builder()
                .description("Ordinateurs Portables")
                .build();
        entityManager.persist(catalogue);

        Produit produit = Produit.builder()
                .name("MacBook Pro")
                .description("Très performant")
                .currentPrice(22000)
                .available(true)
                .catalogue(catalogue)
                .build();
        entityManager.persist(produit);
        entityManager.flush();

        //when
        Produit found = produitRepository.findById(produit.getId()).get();

        //then
        assertThat(produit.getId()).isEqualTo(found.getId());
    }
}
