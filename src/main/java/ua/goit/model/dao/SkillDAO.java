package ua.goit.model.dao;

import org.hibernate.SessionFactory;
import ua.goit.model.entity.Skill;

public class SkillDAO extends AbstractDAO<Skill> {

    public SkillDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Skill.class);
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE Skill s WHERE s.id=:id";
    }
}
