package pp.SpringTest.dao;







import org.springframework.data.jpa.repository.JpaRepository;

import pp.SpringTest.model.User;

import java.util.List;

public interface UserDao {
   void saveUser(User user);
   void updateUser(User user);
   void deleteUser(Long id);
   List<User> findAllUsers();
   User findUserById(Long id);
}
