package fr.grp404.projetjee.persistence.dao.mock;

import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.User;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserDaoMock implements UserDao {

    private ArrayList<User> myUsers = new ArrayList<>();

    public void saveOrUpdate(User user) {
        if (myUsers.contains(user)) {
            int index = myUsers.indexOf(user);
            myUsers.set(index, user);
        } else {
            myUsers.add(user);
        }
    }

    public List<User> findAll() {
        return myUsers;
    }

    public User findByLogin(String login) {
        for (User u : myUsers) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public void deleteUser(User user) {
        myUsers.remove(user);
    }
}
