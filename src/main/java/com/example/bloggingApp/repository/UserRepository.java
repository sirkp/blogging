package com.example.bloggingApp.repository;

import java.util.List;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import com.example.hibernate.configurations.HibernateUtil;
// import com.example.hibernate.entities.UserEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {
   
    @Autowired
    private SessionFactory sessionFactory;
    
    public void save() {
        Session session = sessionFactory.openSession();
        // session.beginTransaction();
        // UserEntity user = new UserEntity();
        // user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		// session.save(us+er);
        // session.getTransaction().commit();
        session.close();
		// HibernateUtil.shutdown();
    }

    // public List<UserEntity> getAll() {
    //     Session session = sessionFactory.openSession();
    //     session.beginTransaction();
    //     List<UserEntity> users = session.createQuery("from UserEntity", UserEntity.class).list();
    //     session.getTransaction().commit();
    //     session.close();
    //     return users;
    // }

    // public UserEntity getUserByUserName(String username) {
    //     Session session = sessionFactory.openSession();
    //     session.beginTransaction();
    //     Query query = session.createQuery("from UserEntity O where O.userName = :usernameParam");
    //     query.setParameter("usernameParam", username);
    //     UserEntity userEntity = (UserEntity) query.uniqueResult();
    //     return userEntity;
    // }

}
