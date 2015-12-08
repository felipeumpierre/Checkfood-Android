package com.checkfood.bean;

import java.io.Serializable;


public class RequestProduct implements Serializable {

    private Product product;
    private String observation;
    private int quantity;

    public RequestProduct() {
    }

    public RequestProduct(int id, Product product, String observation, int quantity) {
        this.product = product;
        this.observation = observation;
        this.quantity = quantity;
    }

    public RequestProduct(Product product, String observation, int quantity) {
        this.product = product;
        this.observation = observation;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

