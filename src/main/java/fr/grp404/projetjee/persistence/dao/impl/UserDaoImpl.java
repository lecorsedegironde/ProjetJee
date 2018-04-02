package fr.grp404.projetjee.persistence.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.User;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;

//TODO
@Singleton
public class UserDaoImpl implements UserDao {

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    public void saveOrUpdate(User user) {

    }

    public List<User> findAll() {
        return null;
    }

    public User findByLogin(String login) {
        return null;
    }

    public void deleteUser(User user) {

    }
}
