package fr.grp404.projetjee.persistence;

import com.google.inject.AbstractModule;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.mock.UserDaoMock;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind les dao sur leurs implémentations
        bind(UserDao.class).to(UserDaoMock.class);
    }
}
