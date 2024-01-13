package com.ems.EmployeeMGTsystem.daoimpl;

import com.ems.EmployeeMGTsystem.dao.RegistrationDao;
import com.ems.EmployeeMGTsystem.entity.Registration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPath;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

    @Autowired
    SessionFactory factory;


    @Override
    public int registerUser(Registration registration) {
        return 0;
    }

    @Override
    public Registration getRegisteredUserByEmail(String email) {
            try (Session session = factory.openSession())
            {
                if (email != null)
                {
                    final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                    final JpaCriteriaQuery<Registration> query = criteriaBuilder.createQuery(Registration.class);
                    final JpaRoot<Registration> from = query.from(Registration.class);

                    final JpaPath<Object> jpaPath = from.get("emailId");
                    final JpaCriteriaQuery<Registration> empFromDB = query.select(from).
                            where(criteriaBuilder.equal(from.get("emailId"), email));
                     Registration singleResult = session.createQuery(empFromDB).getSingleResult();

                    return  singleResult;
                }
                else
                {
                    return null;
                }
            }
        }
    }



