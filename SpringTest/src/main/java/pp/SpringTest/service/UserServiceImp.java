package pp.SpringTest.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pp.SpringTest.dao.UserDao;
import pp.SpringTest.model.User;


import java.util.List;
@Transactional
@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(Long id, String firstName, String lastName, String email) {
        User existingUser = userDao.findUserById(id);
        if (existingUser != null) {
            existingUser.setFirstName(firstName);
            existingUser.setLastName(lastName);
            existingUser.setEmail(email);
            userDao.updateUser(existingUser);
        }
    }

    @Override
    public void updateUserFromEdit(Long id, String firstName, String lastName, String email) {
        User existingUser = userDao.findUserById(id);
        if (existingUser == null) {
            User newUser = new User(firstName, lastName, email);
            userDao.saveUser(newUser);
        } else {
            existingUser.setFirstName(firstName);
            existingUser.setLastName(lastName);
            existingUser.setEmail(email);
            userDao.updateUser(existingUser);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

}
