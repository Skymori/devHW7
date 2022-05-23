package ua.goit.model.dao;

import org.hibernate.SessionFactory;
import ua.goit.model.entity.Customer;

public class CustomerDAO extends AbstractDAO<Customer> {

    public CustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Customer.class);
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE Customer c WHERE c.id=:id";
    }
}
