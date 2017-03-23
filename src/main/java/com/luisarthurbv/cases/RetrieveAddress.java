package com.luisarthurbv.cases;

import com.luisarthurbv.entities.AddressEntity;
import com.luisarthurbv.services.CepService;
import com.luisarthurbv.utils.CepUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveAddress {

    private CepService cepService;

    @Autowired
    public RetrieveAddress(CepService cepService) {
        this.cepService = cepService;
    }

    public AddressEntity retrieve(String cep) throws InvalidCepException, CepNotFoundException {
        if (!CepUtils.isValid(cep)) {
            throw new InvalidCepException(cep);
        }

        String lastCep;
        String currentCep = cep;
        do {
            lastCep = currentCep;
            try {
                return cepService.getAddressByCep(currentCep);
            } catch (CepService.CepNotFoundException e) {
                currentCep = getNewCepTry(currentCep);
            }
        } while (!currentCep.equals(lastCep));

        throw new CepNotFoundException(cep);
    }

    private String getNewCepTry(String cep) {
        return cep.substring(1) + "0";
    }

    public static class InvalidCepException extends RuntimeException {
        public InvalidCepException(String cep) {
            super(String.format("CEP: %s is invalid", cep));
        }
    }

    public static class CepNotFoundException extends RuntimeException {
        public CepNotFoundException(String cep) {
            super(String.format("CEP: %s wasn't find", cep));
        }
    }

}
