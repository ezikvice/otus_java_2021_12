package ru.otus.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.sessionmanager.TransactionManager;
import ru.otus.crm.model.Address;

import java.util.List;
import java.util.Optional;

public class DbServiceAddressImpl implements DBServiceAddress {
    private static final Logger log = LoggerFactory.getLogger(DbServiceAddressImpl.class);

    private final DataTemplate<Address> addressDataTemplate;
    private final TransactionManager transactionManager;

    public DbServiceAddressImpl(TransactionManager transactionManager, DataTemplate<Address> addressDataTemplate) {
        this.transactionManager = transactionManager;
        this.addressDataTemplate = addressDataTemplate;
    }

    @Override
    public Address saveAddress(Address address) {
        return transactionManager.doInTransaction(session -> {
            var addressCloned = address.clone();
            if (address.getId() == null) {
                addressDataTemplate.insert(session, addressCloned);
                log.info("created address: {}", addressCloned);
                return addressCloned;
            }
            addressDataTemplate.update(session, addressCloned);
            log.info("updated address: {}", addressCloned);
            return addressCloned;
        });
    }

    @Override
    public Optional<Address> getAddress(long id) {
        return transactionManager.doInReadOnlyTransaction(session -> {
            var addressOptional = addressDataTemplate.findById(session, id);
            log.info("address: {}", addressOptional);
            return addressOptional;
        });
    }

    @Override
    public List<Address> findAll() {
        return transactionManager.doInReadOnlyTransaction(session -> {
            var addressList = addressDataTemplate.findAll(session);
            log.info("addressList:{}", addressList);
            return addressList;
       });
    }
}
