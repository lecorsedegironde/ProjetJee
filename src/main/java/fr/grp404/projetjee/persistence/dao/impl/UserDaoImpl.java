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
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        String query = "from " + User.class.getName();
        TypedQuery<User> userTypedQuery = em.get().createQuery(query, User.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public List<User> findAllBanned() {
        List<User> banned = new ArrayList<>();
        for (User user : findAll()) {
            if (user.isBan()) {
                banned.add(user);
            }
        }
        return banned;
    }

    @Override
    public List<User> findAllClear() {
        List<User> banned = new ArrayList<>();
        for (User user : findAll()) {
            if (!user.isBan()) {
                banned.add(user);
            }
        }
        return banned;
    }

    @Override
    @Transactional
    public List<User> findByRole(final Role role) {

        String query = "from " + User.class.getName() + " as User" + " where User." + User_.role.getName() + " = :role";
        TypedQuery<User> userTypedQuery = em.get().createQuery(query, User.class).setParameter("role", role);
        return userTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public List<User> findByBirthDate(final LocalDate date) {

        String query = "from " + User.class.getName() + " as User" +
                " where User." + User_.birthDate.getName() + " = :date";
        TypedQuery<User> userTypedQuery = em.get().createQuery(query, User.class).setParameter("date", date);
        return userTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public User findByEmail(final String email) {

        String query = "from " + User.class.getName() + " as User" +
                " where User." + User_.email.getName() + " = :email";

        TypedQuery<User> userTypedQuery = em.get().createQuery(query, User.class).setParameter("email", email);
        List<User> resultList = userTypedQuery.getResultList();

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public User findByLogin(final String login) {

        String query = "from " + User.class.getName() + " as User" +
                " where User." + User_.login.getName() + " = :login";

        TypedQuery<User> userTypedQuery = em.get().createQuery(query, User.class).setParameter("login", login);
        List<User> resultList = userTypedQuery.getResultList();

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
