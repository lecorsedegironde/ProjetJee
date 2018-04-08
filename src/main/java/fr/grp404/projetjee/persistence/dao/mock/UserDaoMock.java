package fr.grp404.projetjee.persistence.dao.mock;

import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;

import java.time.LocalDate;
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

    @Override
    public List<User> findByRole(final Role role) {
        ArrayList<User> myRoleUsers = new ArrayList<>();
        for (User u : myUsers) {
            if (u.getRole().equals(role)) {
                myRoleUsers.add(u);
            }
        }
        return myRoleUsers;
    }

    @Override
    public List<User> findByBirthDate(final LocalDate date) {
        ArrayList<User> myDateUsers = new ArrayList<>();

        for (User u : myUsers) {
            if (u.getBirthDate().isEqual(date)) {
                myDateUsers.add(u);
            }
        }

        return myDateUsers;
    }

    @Override
    public User findByEmail(final String email) {
        for (User u : myUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public User findByLogin(final String login) {
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
