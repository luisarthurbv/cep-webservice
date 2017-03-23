package com.luisarthurbv.models;

import com.luisarthurbv.entities.AddressEntity;

public class Address {

    private String cep;
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    public Address() {}

    public Address(AddressEntity entity) {
        this.cep = entity.getCep();
        this.street = entity.getStreet();
        this.neighborhood = entity.getNeighborhood();
        this.city = entity.getCity();
        this.state = entity.getState();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
