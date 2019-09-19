package com.nexio.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Catalogue.ENTITY_NAME)
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
public class Catalogue extends AbstractBaseEntity {

    public static final String ENTITY_NAME = "CATALOGUE_PRODUITS";

    @NotBlank
    private String description;

    @OneToMany(mappedBy = "catalogue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Produit> produits = new ArrayList<>();


}
