package ru.otus.crm.service;

import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;

import java.util.List;
import java.util.Optional;

public interface DBServiceAddress {

    Address saveAddress(Address address);

    Optional<Address> getAddress(long id);

    List<Address> findAll();
}
