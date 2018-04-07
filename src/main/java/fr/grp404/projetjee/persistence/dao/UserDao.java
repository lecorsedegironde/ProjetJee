package fr.grp404.projetjee.persistence.dao;

import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;

import java.time.LocalDate;
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
     * @param role the searched role
     * @return all the user of the given role in the database
     */
    List<User> findByRole(final Role role);

    /**
     * @param date the searched LocalDate
     * @return all the user of the given birthdate in the database
     */
    List<User> findByBirthDate(final LocalDate date);

    /**
     * @param email the searched email, must be case sensitive and exact match. Null email returns null result
     * @return the user of the given email in the database
     */
    User findByEmail(final String email);

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
