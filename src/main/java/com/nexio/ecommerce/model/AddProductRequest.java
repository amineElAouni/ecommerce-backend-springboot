package com.nexio.ecommerce.model;

import lombok.Data;

@Data
public class AddProductRequest {

    private long idProduct;

    private int quantite;
}
