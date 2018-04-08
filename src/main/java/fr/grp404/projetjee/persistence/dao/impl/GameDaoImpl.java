package fr.grp404.projetjee.persistence.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.Game_;
import fr.grp404.projetjee.persistence.domain.UserGame;
import fr.grp404.projetjee.persistence.domain.UserGame_;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class GameDaoImpl implements GameDao {

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(Game game) {
        this.em.get().persist(game);
    }

    @Override
    @Transactional
    public List<Game> findAll() {

        String query = "from " + Game.class.getName();
        TypedQuery<Game> gameTypedQuery = em.get().createQuery(query, Game.class);

        return gameTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public Game findByName(String name) {

        String query = "from " + Game.class.getName() + " as Game" +
                " where Game." + Game_.name.getName() + " = :name";

        TypedQuery<Game> gameTypedQuery = em.get().createQuery(query, Game.class).setParameter("name", name);
        List<Game> resultList = gameTypedQuery.getResultList();

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public int findNumberPlayingGame(Game game) {
        String query = "select Game from " + UserGame.class.getName() + " as UserGame," + Game.class.getName() + " as Game"
                + " where UserGame." + UserGame_.game.getName() + " = Game." + Game_.id.getName()
                + " and Game." + Game_.id.getName() + " = :id";

        TypedQuery<Game> gameTypedQuery = em.get().createQuery(query, Game.class).setParameter("id", game.getId());
        return gameTypedQuery.getResultList().size();
    }

    @Override
    @Transactional
    public void deleteGame(Game game) {
        if (game != null) {
            this.em.get().remove(game);
        }
    }
}
