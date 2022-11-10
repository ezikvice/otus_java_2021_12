package ru.dimk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimk.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}