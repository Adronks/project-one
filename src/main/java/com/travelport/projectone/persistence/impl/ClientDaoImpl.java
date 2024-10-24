package com.travelport.projectone.persistence.impl;

import com.travelport.projectone.entities.Client;
import com.travelport.projectone.persistence.ClientDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientDaoImpl implements ClientDao {

    @PersistenceContext // JPA
    private EntityManager entityManager;

    @Override
    public void save(Client client) {
        entityManager.persist(client);
    }

    @Override
    public List<Client> list() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    @Override
    public Optional<Client> getClientById(Integer id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }

    @Override
    public void update(Client client) {
        entityManager.merge(client);
    }

    @Override
    public Optional<Integer> deleteById(Integer id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
            return Optional.of(id);
        }
        return Optional.empty();
    }

}
