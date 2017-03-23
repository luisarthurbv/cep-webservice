package com.luisarthurbv.services;

import com.luisarthurbv.entities.AddressEntity;
import com.luisarthurbv.repositories.AddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CepServiceTest {

    private static Map<String, AddressEntity> cepMockAddressMap = new HashMap<>();

    static {
        AddressEntity address = new AddressEntity();
        address.setId(1);
        address.setCep("11111111");
        address.setStreet("street dummy 1");
        address.setNeighborhood("neighborhood dummy 1");
        address.setCity("city dummy 1");
        address.setStreet("RJ");
        cepMockAddressMap.put(address.getCep(), address);
    }

    @Test
    public void validCepTest() {
        String cep = "11111111";
        AddressRepository repository = mock(AddressRepository.class);
        AddressEntity address = cepMockAddressMap.get(cep);
        when(repository.findByCep(cep)).thenReturn(address);

        CepService cepService = new CepService(repository);
        AddressEntity addressRetrieved = cepService.getAddressByCep(cep);
        Assert.assertEquals(String.format("CepService retruns valid cep"),
                address, addressRetrieved);
    }

    @Test(expected = CepService.CepNotFoundException.class)
    public void cepNotFoundTest() {
        String cep = "11111111";
        AddressRepository repository = mock(AddressRepository.class);

        CepService cepService = new CepService(repository);
        cepService.getAddressByCep(cep);
    }

}
