package ru.dimk.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimk.crm.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Override
    <S extends Client> S save(S entity);

    @Override
    <S extends Client> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Client> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Client> findAll();

    @Override
    Iterable<Client> findAllById(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Client entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Client> entities);

    @Override
    void deleteAll();
}