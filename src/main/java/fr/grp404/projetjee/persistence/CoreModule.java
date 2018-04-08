package fr.grp404.projetjee.persistence;

import com.google.inject.AbstractModule;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.dao.impl.UserDaoImpl;
import fr.grp404.projetjee.persistence.dao.mock.GameDaoMock;
import fr.grp404.projetjee.persistence.dao.mock.UserDaoMock;
import fr.grp404.projetjee.persistence.dao.mock.UserGameDaoMock;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind les dao sur leurs implémentations
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(GameDao.class).to(GameDaoMock.class);
        bind(UserGameDao.class).to(UserGameDaoMock.class);
    }
}
