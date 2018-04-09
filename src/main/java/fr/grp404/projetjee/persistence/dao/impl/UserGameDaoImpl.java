package fr.grp404.projetjee.persistence.dao.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.*;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.util.List;

public class UserGameDaoImpl implements UserGameDao {

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(UserGame userGame) {
        this.em.get().persist(userGame);
    }

    @Override
    @Transactional
    public List<UserGame> findAll() {

        String query = "from " + UserGame.class.getName();
        TypedQuery<UserGame> userGameTypedQuery = em.get().createQuery(query, UserGame.class);

        return userGameTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public List<UserGame> findByGame(Game game) {

        String query = "from " + UserGame.class.getName() + " as UserGame" +
                " where UserGame." + UserGame_.game.getName() + " = :game";

        TypedQuery<UserGame> userGameTypedQuery = em.get().createQuery(query, UserGame.class)
                .setParameter("game", game);

        return userGameTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public List<UserGame> findByUser(User user) {

        String query = "select ug from UserGame ug join ug.user u where u.id = :user";

        TypedQuery<UserGame> userGameTypedQuery = em.get().createQuery(query, UserGame.class)
                .setParameter("user", user.getId());

        return userGameTypedQuery.getResultList();
    }

    @Override
    public UserGame findCurrentByUser(User user) {
        List<UserGame> userGames = findByUser(user);
        for (UserGame g : userGames) {
            if (g.getEndDate() == null) {
                return g;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public List<UserGame> findByStartDate(LocalDateTime date) {

        String query = "from " + User.class.getName() + " as UserGame" +
                " where UserGame." + UserGame_.startDate.getName() + " = :date";
        TypedQuery<UserGame> userGameTypedQuery = em.get().createQuery(query, UserGame.class)
                .setParameter("date", date);

        return userGameTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public List<UserGame> findByEndDate(LocalDateTime date) {

        String query = "from " + User.class.getName() + " as UserGame" +
                " where UserGame." + UserGame_.endDate.getName() + " = :date";
        TypedQuery<UserGame> userGameTypedQuery = em.get().createQuery(query, UserGame.class)
                .setParameter("date", date);

        return userGameTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void deleteUser(UserGame userGame) {
        if (userGame != null) {
            this.em.get().remove(userGame);
        }
    }
}
