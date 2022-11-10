package ru.dimk.service;

import org.springframework.stereotype.Service;
import ru.dimk.model.Client;
import ru.dimk.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        Iterable<Client> all = clientRepository.findAll();
        return (List<Client>) all;
    }

    @Override
    public Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client findRandom() {
        List<Client> clients = (List<Client>) clientRepository.findAll();
        Random r = new Random();
        return clients.stream().skip(r.nextInt(clients.size() - 1)).findFirst().orElse(null);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
