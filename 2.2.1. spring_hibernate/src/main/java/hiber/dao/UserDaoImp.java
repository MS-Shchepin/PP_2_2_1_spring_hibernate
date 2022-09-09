package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserHasCar(Car car) {
//      String hql = "from User where car.series = %d and car.model = '%s'".formatted(car.getSeries(), car.getModel());
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "from User where car.series = :series and car.model = :model");
      query.setParameter("series", car.getSeries());
      query.setParameter("model", car.getModel());
      return query.getSingleResult();
   }
}
