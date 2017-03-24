package com.luisarthurbv.controllers.v1;

import com.luisarthurbv.cases.RetrieveAddress;
import com.luisarthurbv.entities.AddressEntity;
import com.luisarthurbv.models.Address;
import com.luisarthurbv.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cep/v1")
public class CepController {

    public RetrieveAddress retrieveAddress;

    @Autowired
    public CepController(RetrieveAddress retrieveAddress) {
        this.retrieveAddress = retrieveAddress;
    }

    @RequestMapping(value = "/getAddress", method = RequestMethod.POST)
    public ResponseEntity<Map> retrieveAddress(@RequestBody RetrieveAddressRequest r) {
        try {
            AddressEntity entity = retrieveAddress.retrieve(r.cep);
            Map body = MapUtils.convertObject(new RetrieveAddressResponse(new Address(entity)));
            return new ResponseEntity<Map>(body, HttpStatus.OK);
        } catch (RetrieveAddress.InvalidCepException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", String.format("CEP: %s is invalid", r.cep));
            return new ResponseEntity<Map>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (RetrieveAddress.CepNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", String.format("CEP: %s wasn't found", r.cep));
            return new ResponseEntity<Map>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", String.format("Unknown error", r.cep));
            return new ResponseEntity<Map>(errorResponse, HttpStatus.METHOD_FAILURE);
        }
    }

    public static class RetrieveAddressRequest {

        private String cep;

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

    }

    public static class RetrieveAddressResponse {

        private Address address;

        public RetrieveAddressResponse(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

    }

}
