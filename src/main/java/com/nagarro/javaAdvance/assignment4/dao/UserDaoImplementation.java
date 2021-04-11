package com.nagarro.javaAdvance.assignment4.dao;

import com.nagarro.javaAdvance.assignment4.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserDaoImplementation implements UserDao {
    HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public void saveUser(User user) {
        Session session = template.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public User getUser(String userId) throws DataAccessException {
        List<User> listUser = template.execute(session -> {
            @SuppressWarnings("unchecked")
            List<User> result = (List<User>) session.createQuery("from User where userId=" + "'" + userId + "'").list();
            return result;
        });
        if (listUser.isEmpty()) {
            return null;
        }
        return listUser.get(0);
    }
}
