package fr.grp404.projetjee.persistence.dao;

import fr.grp404.projetjee.persistence.domain.Game;

import java.util.List;

public interface GameDao {

    /**
     * Persist game
     *
     * @param game object to persist
     */
    void saveOrUpdate(Game game);

    /**
     * @return all games in database
     */
    List<Game> findAll();

    /**
     * @param name the searched name, must be case sensitive and exact match. Null name returns null result
     * @return the game or null if nothing found
     */
    Game findByName(final String name);

    /**
     * Delete game
     *
     * @param game to delete
     */
    void deleteGame(Game game);
}
