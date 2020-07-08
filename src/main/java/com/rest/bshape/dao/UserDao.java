package com.rest.bshape.dao;

import com.rest.bshape.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    private void delete(User user) {
        entityManager.remove(entityManager.contains(user)
                ?
                user
                :
                entityManager.merge(user));
    }

    public List<User> findAll(){
        return this.entityManager.createQuery("SELECT u FROM User u").getResultList();
    }
}
