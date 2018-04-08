package fr.grp404.projetjee.persistence.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.User_;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

//TODO
@Singleton
public class UserDaoImpl implements UserDao {

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(User user) {
        this.em.get().persist(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        StringBuilder query = new StringBuilder("from ");
        query.append(User.class.getName());
        return (List<User>) em.get().createQuery(query.toString()).getResultList();
    }

    @Override
    @Transactional
    public List<User> findByRole(final Role role) {
        StringBuilder query = new StringBuilder("from ");
        query.append(User.class.getName()).append(" as User");
        query.append(" where User.").append(User_.role.getName()).append(" = :role");

        return (List<User>) em.get().createQuery(query.toString()).setParameter("role", role).getResultList();
    }

    @Override
    @Transactional
    public List<User> findByBirthDate(final LocalDate date) {
        StringBuilder query = new StringBuilder("from ");
        query.append(User.class.getName()).append(" as User");
        query.append(" where User.").append(User_.birthDate.getName()).append(" = :date");

        return (List<User>) em.get().createQuery(query.toString()).setParameter("date", date).getResultList();
    }

    @Override
    @Transactional
    public User findByEmail(final String email) {
        StringBuilder query = new StringBuilder("from ");
        query.append(User.class.getName()).append(" as User");
        query.append(" where User.").append(User_.email.getName()).append(" = :email");

        List<User> resultList = em.get().createQuery(query.toString()).setParameter("email", email).getResultList();

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public User findByLogin(final String login) {
        StringBuilder query = new StringBuilder("from ");
        query.append(User.class.getName()).append(" as User");
        query.append(" where User.").append(User_.login.getName()).append(" = :login");

        List<User> resultList = em.get().createQuery(query.toString()).setParameter("login", login).getResultList();

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        if (user != null) {
            this.em.get().remove(user);
        }
    }
}
