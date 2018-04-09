package fr.grp404.projetjee.persistence;

import com.google.inject.AbstractModule;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.dao.impl.GameDaoImpl;
import fr.grp404.projetjee.persistence.dao.impl.UserDaoImpl;
import fr.grp404.projetjee.persistence.dao.impl.UserGameDaoImpl;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind les dao sur leurs implémentations
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(fr.grp404.projetjee.persistence.dao.GameDao.class).to(GameDaoImpl.class);
        bind(UserGameDao.class).to(UserGameDaoImpl.class);
    }
}
