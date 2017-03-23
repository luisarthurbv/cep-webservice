package com.luisarthurbv.cases;

import com.luisarthurbv.entities.AddressEntity;
import com.luisarthurbv.services.CepService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveAddressTest {

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

        address = new AddressEntity();
        address.setId(2);
        address.setCep("22222220");
        address.setStreet("street dummy 2");
        address.setNeighborhood("neighborhood dummy 2");
        address.setCity("city dummy 2");
        address.setStreet("SP");
        cepMockAddressMap.put(address.getCep(), address);
    }

    @Test
    public void firstTryValidCepTest() {
        String cep = "11111111";
        CepService cepServiceMock = mock(CepService.class);
        AddressEntity address = cepMockAddressMap.get(cep);
        when(cepServiceMock.getAddressByCep(cep)).thenReturn(address);

        RetrieveAddress retrieveAddress = new RetrieveAddress(cepServiceMock);
        AddressEntity addressRetrieved = retrieveAddress.retrieve(cep);
        Assert.assertEquals(String.format("RetrieveAddress retruns valid cep"),
                address, addressRetrieved);
    }

    @Test
    public void secondTryValidCepTest() {
        String correctCep = "22222220";
        String cep = "22222222";
        CepService cepServiceMock = mock(CepService.class);
        AddressEntity address = cepMockAddressMap.get(correctCep);
        when(cepServiceMock.getAddressByCep(correctCep)).thenReturn(address);
        doThrow(new CepService.CepNotFoundException(correctCep)).when(cepServiceMock).getAddressByCep(cep);

        RetrieveAddress retrieveAddress = new RetrieveAddress(cepServiceMock);
        AddressEntity addressRetrieved = retrieveAddress.retrieve(cep);
        Assert.assertEquals(String.format("RetrieveAddress returns valid cep"),
                address, addressRetrieved);
    }

    @Test(expected = RetrieveAddress.InvalidCepException.class)
    public void invalidCepTest() {
        CepService cepServiceMock = mock(CepService.class);
        RetrieveAddress retrieveAddress = new RetrieveAddress(cepServiceMock);
        retrieveAddress.retrieve("");
    }

    @Test(expected = RetrieveAddress.CepNotFoundException.class)
    public void cepNotFoundTest() {
        String cep = "55555555";
        CepService cepServiceMock = mock(CepService.class);
        doThrow(new RetrieveAddress.CepNotFoundException(cep)).when(cepServiceMock).getAddressByCep(cep);
        RetrieveAddress retrieveAddress = new RetrieveAddress(cepServiceMock);
        retrieveAddress.retrieve(cep);
    }

}
