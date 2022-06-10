package ru.dimk.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimk.crm.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}