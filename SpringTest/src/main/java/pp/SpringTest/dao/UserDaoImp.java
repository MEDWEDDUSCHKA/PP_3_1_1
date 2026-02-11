package pp.SpringTest.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pp.SpringTest.model.User;


import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String QUERY_FIND_ALL_USERS = "SELECT u FROM User u";

    @Override
    public void saveUser(User user){
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user){
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id){
        User user = entityManager.find(User.class, id);
        if(user != null){
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> findAllUsers() {
        TypedQuery<User> query = entityManager.createQuery(QUERY_FIND_ALL_USERS, User.class);
        return query.getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
