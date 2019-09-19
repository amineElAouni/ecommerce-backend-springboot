package com.nexio.ecommerce;

import com.nexio.ecommerce.model.Catalogue;
import com.nexio.ecommerce.model.Produit;
import com.nexio.ecommerce.model.User;
import com.nexio.ecommerce.repository.CatalogueRepository;
import com.nexio.ecommerce.repository.ProduitRepository;
import com.nexio.ecommerce.repository.UserRepository;
import com.nexio.ecommerce.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Catalogue catalogue = new Catalogue();
        catalogue.setDescription("Ordinateurs Portables");

        Produit p1 = new Produit();
        p1.setName("MacBook Air");
        p1.setAvailable(true);
        p1.setCurrentPrice(16190.45);
        p1.setDescription("Bonne Configuration");
        p1.setCatalogue(catalogue);

        Produit p2 = new Produit();
        p2.setName("MacBook Pro");
        p2.setAvailable(false);
        p2.setCurrentPrice(21340.95);
        p2.setDescription("Très bonne configuration");
        p2.setCatalogue(catalogue);

        catalogue.getProduits().add(p1);
        catalogue.getProduits().add(p2);

        catalogueRepository.save(catalogue);

        User user = new User("ADMIN", "ADMIN");
        userRepository.save(user);

    }
}
