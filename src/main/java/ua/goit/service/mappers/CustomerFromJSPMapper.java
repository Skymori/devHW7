package ua.goit.service.mappers;

import ua.goit.exceptions.DAOException;
import ua.goit.model.entity.Customer;
import ua.goit.model.entity.Project;
import ua.goit.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerFromJSPMapper implements EntityFromJSPMapper<Customer> {
    private final Service<Project> projectService;

    public CustomerFromJSPMapper(Service<Project> projectService) {
        this.projectService = projectService;
    }

    @Override
    public Customer readJSPForm(HttpServletRequest req) {
        Customer customer = new Customer();
        customer.setName(req.getParameter("name"));
        customer.setIndustry(req.getParameter("industry"));
        return customer;
    }

    @Override
    public Customer readJSPForm(HttpServletRequest req, Customer customer) {
        customer.setName(req.getParameter("name"));
        customer.setIndustry(req.getParameter("industry"));

        String[] listOfProjectId = req.getParameterValues("projects");
        Set<Project> projects = new HashSet<>();
        if (listOfProjectId != null && listOfProjectId.length > 0) {
            projects = Arrays.stream(listOfProjectId)
                    .mapToInt(Integer::parseInt)
                    .mapToObj(id -> {
                        Project project = null;
                        try {
                            project = projectService.findById(id);
                        } catch (DAOException e) {
                            e.printStackTrace();
                        }
                        return project;
                    })
                    .collect(Collectors.toSet());
        }
        customer.setProjects(projects);

        return customer;
    }
}
