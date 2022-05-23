package ua.goit.service;

import ua.goit.exceptions.DAOException;
import ua.goit.model.dao.GenericDAO;

import java.util.Set;

public class Service<T> {
    private final GenericDAO<T> repository;

    public Service(GenericDAO<T> repository) {
        this.repository = repository;
    }

    public void create(T company) throws DAOException {
        repository.create(company);
    }

    public void update(T entity) throws DAOException {
        repository.update(entity);
    }

    public void delete(int id) throws DAOException {
        repository.delete(id);
    }

    public T findById(int id) throws DAOException {
        return repository.read(id);
    }

    public Set<T> readAll() {
        return repository.readAll();
    }

}
