package fr.grp404.projetjee.persistence.dao.mock;

import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserGameDaoMock implements UserGameDao {

    private ArrayList<UserGame> myUserGames = new ArrayList<>();

    @Override
    public void saveOrUpdate(UserGame userGame) {
        if (myUserGames.contains(userGame)) {
            int index = myUserGames.indexOf(userGame);
            myUserGames.set(index, userGame);
        } else {
            myUserGames.add(userGame);
        }
    }

    @Override
    public List<UserGame> findAll() {
        return myUserGames;
    }

    @Override
    public List<UserGame> findByGame(Game game) {
        ArrayList<UserGame> myGameUserGames = new ArrayList<>();
        for (UserGame u : myUserGames) {
            if (u.getGame().equals(game)) {
                myGameUserGames.add(u);
            }
        }
        return myGameUserGames;
    }

    @Override
    public List<UserGame> findByUser(User user) {
        ArrayList<UserGame> myUserUserGames = new ArrayList<>();
        for (UserGame u : myUserGames) {
            if (u.getUser().equals(user)) {
                myUserUserGames.add(u);
            }
        }
        return myUserUserGames;
    }

    @Override
    public UserGame findCurrentByUser(User user) {
        List<UserGame> byUser = findByUser(user);
        for (UserGame g : byUser) {
            if (g.getEndDate() == null) {
                return g;
            }
        }
        return null;
    }

    @Override
    public List<UserGame> findByStartDate(LocalDateTime date) {
        ArrayList<UserGame> myStartDateUserGames = new ArrayList<>();
        for (UserGame u : myUserGames) {
            if (u.getStartDate().isEqual(date)) {
                myStartDateUserGames.add(u);
            }
        }
        return myStartDateUserGames;
    }

    @Override
    public List<UserGame> findByEndDate(LocalDateTime date) {
        ArrayList<UserGame> myEndDateUserGames = new ArrayList<>();
        for (UserGame u : myUserGames) {
            if (u.getEndDate() != null) {
                if (u.getEndDate().isEqual(date)) {
                    myEndDateUserGames.add(u);
                }
            }
        }
        return myEndDateUserGames;
    }

    @Override
    public void deleteUserGame(UserGame userGame) {
        myUserGames.remove(userGame);
    }
}
