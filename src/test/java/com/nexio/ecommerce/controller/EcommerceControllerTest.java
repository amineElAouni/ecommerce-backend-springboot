package com.nexio.ecommerce.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class EcommerceControllerTest extends AbstractTest {

    private final static String URL_ROOT_PATH = "/api/v1";
    private final static String URL_GET_PRODUCT = "/produits/1";
    private final static String URL_GET_CATALOG = "/catalogues/1";
    private final static String URL_CATALOG_OF_PRODUCT = "/catalogues/1/produits";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void should_return_product_given_id() throws Exception {
        String uri = URL_ROOT_PATH + URL_GET_PRODUCT;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    public void should_return_catalogue_given_id() throws Exception {
        String uri = URL_ROOT_PATH + URL_GET_CATALOG;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    public void should_return_list_of_products_given_id_catalog() throws Exception {
        String uri = URL_ROOT_PATH + URL_CATALOG_OF_PRODUCT;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }
}
