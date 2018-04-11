package fr.grp404.projetjee.persistence.dao;

import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

import java.time.LocalDateTime;
import java.util.List;

public interface UserGameDao {

    /**
     * Persist User
     *
     * @param userGame object to persist
     */
    void saveOrUpdate(UserGame userGame);

    /**
     * @return all the user games in database
     */
    List<UserGame> findAll();

    /**
     * @param game the concerned game
     * @return all the user games of the provided game
     */
    List<UserGame> findByGame(final Game game);

    /**
     * @param user the concerned user
     * @return all the user games of the provided user
     */
    List<UserGame> findByUser(final User user);

    /**
     * @param user user the concerned user
     * @return the current game played by the user, null if none exist
     */
    UserGame findCurrentByUser(final User user);

    /**
     * @param date start date
     * @return all the user game that started at the provided date
     */
    List<UserGame> findByStartDate(final LocalDateTime date);

    /**
     * @param date end  date
     * @return all the user game that ended at the provided date
     */
    List<UserGame> findByEndDate(final LocalDateTime date);

    /**
     * Delete user game
     *
     * @param userGame to delete
     */
    void deleteUserGame(UserGame userGame);
}
