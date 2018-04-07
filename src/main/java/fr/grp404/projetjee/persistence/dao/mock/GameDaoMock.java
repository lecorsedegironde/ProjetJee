package fr.grp404.projetjee.persistence.dao.mock;

import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.domain.Game;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class GameDaoMock implements GameDao {

    private ArrayList<Game> myGames = new ArrayList<>();

    @Override
    public void saveOrUpdate(Game game) {
        if (myGames.contains(game)) {
            int index = myGames.indexOf(game);
            myGames.set(index, game);
        } else {
            myGames.add(game);
        }
    }

    @Override
    public List<Game> findAll() {
        return myGames;
    }

    @Override
    public Game findByName(String name) {
        for (Game g : myGames) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public void deleteGame(Game game) {
        myGames.remove(game);
    }
}
