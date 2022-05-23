package ua.goit.model.dao;

import org.hibernate.SessionFactory;
import ua.goit.model.entity.Company;

public class CompanyDAO extends AbstractDAO<Company> {

    public CompanyDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Company.class);
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE Company c WHERE c.id=:id";
    }
}
