package ua.goit.controller;

import org.hibernate.SessionFactory;
import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.model.dao.CustomerDAO;
import ua.goit.model.dao.ProjectDAO;
import ua.goit.model.entity.Customer;
import ua.goit.model.entity.Project;
import ua.goit.service.Service;
import ua.goit.service.mappers.CustomerFromJSPMapper;
import ua.goit.service.mappers.EntityFromJSPMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

;

@WebServlet("/customers/*")
public class CustomerServlet extends AbstractServlet<Customer> {
    private final SessionFactory sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    private final Service<Project> projectService = new Service<>(new ProjectDAO(sessionFactory));

    @Override
    protected EntityFromJSPMapper<Customer> initMapper() {
        return new CustomerFromJSPMapper(projectService);
    }

    @Override
    protected Service<Customer> initService() {
        return new Service<>(new CustomerDAO(sessionFactory));
    }

    @Override
    protected String getServletPath() {
        return "/customers";
    }

    @Override
    protected String getEntitiesPage() {
        return "/view/customers.jsp";
    }

    @Override
    protected String getEntityPage() {
        return "/view/customer.jsp";
    }

    @Override
    protected String getFormPage() {
        return "/view/customerForm.jsp";
    }

    @Override
    protected void setAdditionalAttributesInForm(HttpServletRequest req) {
        Set<Project> projectList = projectService.readAll();
        req.setAttribute("projectList", projectList);
    }
}
