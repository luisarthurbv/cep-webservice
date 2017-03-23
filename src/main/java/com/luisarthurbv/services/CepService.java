package com.luisarthurbv.services;

import com.luisarthurbv.entities.AddressEntity;
import com.luisarthurbv.repositories.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    private AddressRepository addressRepository;

    @Autowired
    public CepService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressEntity getAddressByCep(String cep) throws CepNotFoundException {
        AddressEntity entity = addressRepository.findByCep(cep);
        if (entity != null) {
            return entity;
        } else {
            throw new CepNotFoundException(cep);
        }
    }

    public static class CepNotFoundException extends RuntimeException {

        public CepNotFoundException(String cep) {
            super(String.format("Couldn't find cep: %s", cep));
        }

    }

}
