package com.luisarthurbv.repositories;

import com.luisarthurbv.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, String> {

    public AddressEntity findByCep(String cep);

}
