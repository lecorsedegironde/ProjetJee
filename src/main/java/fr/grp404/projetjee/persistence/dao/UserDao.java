package fr.grp404.projetjee.persistence.dao;

import fr.grp404.projetjee.persistence.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * Persist User
     *
     * @param user object to persist
     */
    void saveOrUpdate(User user);

    /**
     * @return all the user in database
     */
    List<User> findAll();

    /**
     * @param login the searched login, must be case sensitive and exact match. Null login returns null result
     * @return the user or null if nothing found
     */
    User findByLogin(final String login);

    /**
     * Delete user
     *
     * @param user to delete
     */
    void deleteUser(User user);
}
