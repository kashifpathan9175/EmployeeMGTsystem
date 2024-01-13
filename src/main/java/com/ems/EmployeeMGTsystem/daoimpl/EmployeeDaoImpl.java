package com.ems.EmployeeMGTsystem.daoimpl;

import java.util.List;

import com.ems.EmployeeMGTsystem.entity.Registration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.ems.EmployeeMGTsystem.dao.EmployeeDao;
import com.ems.EmployeeMGTsystem.entity.Country;
import com.ems.EmployeeMGTsystem.entity.Employee;

import jakarta.persistence.criteria.Root;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    SessionFactory factory;

    @Override
    public Registration login(String email, String password)
    {

            try (Session session = factory.openSession()) {
                if (email != null && password != null) {
                    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                    CriteriaQuery<Registration> criteriaQuery = criteriaBuilder.createQuery(Registration.class);
                    Root<Registration> root = criteriaQuery.from(Registration.class);
                    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email), criteriaBuilder.equal(root.get("password"), password));
                    Registration employees = session.createQuery(criteriaQuery).getSingleResult();
                    return employees;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        return  null;
    }

    @Override
    public int registerUser(Registration registration) {

        try(Session session = factory.openSession()) {

            if (registration != null) {

            }
        }
        return 0;
    }

    @Override
    public List<Employee> getAllEmployee() {

        try (Session session = factory.openSession()) {

            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<Employee> createQuery = criteriaBuilder.createQuery(Employee.class);

            Root<Employee> from = createQuery.from(Employee.class);

            List<Employee> resultList = session.createQuery(createQuery.select(from)).getResultList();

            return resultList;


        } catch (Exception e) {


        }
        return null;
    }

    @Override
    public int addEmployee(Employee employee) {
        int status = 0;
        if (employee != null) {
            try (Session session = factory.openSession()) {
                Country country = employee.getCountry();

                if (country != null) {
                    final Country existingCountry = getCountryByNameAndCode(country.getCname(), country.getCountryCode());

                    if (existingCountry == null) {
                        // Save the country only if it doesn't exist
                        session.save(country);
                        session.beginTransaction().commit();
                        // Check existing employee by email
                        final Employee existingEmailEmployee = getEmployeeByemailId(employee.getEmailId());

                        if (existingEmailEmployee == null) {
                            // Check existing employee by phone number
                            final Employee existingPhoneNoEmployee = getEmployeeByPhoneNo(employee.getPhoneNo());

                            if (existingPhoneNoEmployee == null) {
                                // Check existing employee by password
                                final Employee existingPasswordEmployee = getEmployeeByPassword(employee.getPassword());

                                if (existingPasswordEmployee == null) {
                                    // Save the employee only if email, phone, and password are not in use
                                    session.save(employee);
                                    session.beginTransaction().commit();
                                    status = 1; // Success
                                } else {
                                    // Rollback the transaction if password is in use
                                    session.beginTransaction().rollback();
                                    status = 6; // Password already in use
                                }
                            } else {
                                // Rollback the transaction if phone number is in use
                                session.beginTransaction().rollback();
                                status = 5; // Phone number already in use
                            }
                        } else {
                            // Rollback the transaction if email is in use
                            session.beginTransaction().rollback();
                            status = 4; // Email address already in use
                        }
                    } else {
                        // If the country exists, set it on the employee
                        employee.setCountry(existingCountry);

                        // Save the employee only if the country exists
                        session.save(employee);
                        session.beginTransaction().commit();
                        status = 1; // Success
                    }
                } else {
                    status = 3; // Country cannot be empty
                }
            } catch (ConstraintViolationException e) {
                e.printStackTrace();
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
            }
        } else {
            status = 2; // Employee is null
        }

        return status;
    }


    @Override
    public int updateEmployeeById(long eid, Employee employee) {

        int status = 0;
        try (Session session = factory.openSession()) {

            final Employee employeefromDb = session.get(Employee.class, eid);

            if (employeefromDb != null) {
                if (employeefromDb.getEmailId() != employee.getEmailId()) {
                    if (employeefromDb.getPhoneNo() != employee.getPhoneNo()) {
                        if (employeefromDb.getPassword() != employee.getPassword()) {
                            session.saveOrUpdate(employee);
                            session.beginTransaction().commit();
                            status = 1;
                        } else {
                            status = 5;
                        }
                    } else {
                        status = 4;
                    }
                } else {
                    status = 3;
                }

            } else {
                status = 2;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int updateEmployeeByName(String employeeName, Employee employee) {
        int status = 0;

        try (Session session = factory.openSession()) {

            final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final JpaCriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
            final Root<Employee> from = query.from(Employee.class);
            query.select(from).where(criteriaBuilder.equal(from.get("ename"), employeeName));
//			if(employeeName.equals())
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public String deleteEmployeeById(long eid) {
        String msg = null;
        try (Session session = factory.openSession()) {

            final Employee employeeFromDB = session.get(Employee.class, eid);
            if (employeeFromDB != null) {
                session.delete(employeeFromDB);
                session.beginTransaction().commit();
                msg = "Employee deleted";
            } else {
                msg = "Employee not found";
            }
        }
        return msg;
    }

    @Override
    public List<Employee> getEmployeeByName(String ename) {

        try (Session session = factory.openSession()) {

            final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final JpaCriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
            final Root<Employee> root = query.from(Employee.class);

            query.select(root).where(criteriaBuilder.equal(root.get("ename"), ename));
            List<Employee> resultList = session.createQuery(query).getResultList();

            if (resultList.contains(ename)) {
                return resultList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeByCountry(String cname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee getEmployeeByPhoneNo(String phoneNo) {
        if(phoneNo != null) {
            try (Session session = factory.openSession()) {
                final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                final JpaCriteriaQuery<Object> query = criteriaBuilder.createQuery();
                final JpaRoot<Employee> root = query.from(Employee.class);
                final JpaPredicate phoneNoFromDb = criteriaBuilder.equal(root.get("phoneNo"), phoneNo);

                query.select(root).where(phoneNoFromDb);
                final Object isAlreadyExists = session.createQuery(query).getSingleResult();

                return (Employee) isAlreadyExists;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public Employee getEmployeeByemailId(String emailId) {

        try (Session session = factory.openSession())
        {
            if (emailId != null)
            {
                final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                final JpaCriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
                final JpaRoot<Employee> from = query.from(Employee.class);

                final JpaPath<Object> jpaPath = from.get("emailId");
                final JpaCriteriaQuery<Employee> empFromDB = query.select(from).
                        where(criteriaBuilder.equal(from.get("emailId"), emailId));
                final Employee singleResult = session.createQuery(empFromDB).getSingleResult();

                return  singleResult;
            }
            else
            {
                return null;
            }
        }
    }

    @Override
    public Employee getEmployeeByDepartment(String department) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee getEmployeeByPassword(String password) {

        if (password != null)
        {
            try (Session session = factory.openSession())
            {
                final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                final JpaCriteriaQuery<Object> query = criteriaBuilder.createQuery();
                final Root<Employee> root = query.from(Employee.class);
                query.where(criteriaBuilder.equal(root.get("password"), password));

                final Object employeeFromDB = session.createQuery(query).uniqueResult();

                return (Employee) employeeFromDB;
            }
        }
        else
        {
            return null;
        }

    }

    @Override
    public List<Country> getAllCountry() {

        try (Session session = factory.openSession()) {

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            final CriteriaQuery<Country> query = criteriaBuilder.createQuery(Country.class);
            final Root<Country> root = query.from(Country.class);

            final List<Country> resultList = session.createQuery(query).getResultList();

            return resultList;
        }

    }

    @Override
    public String addCountry(Country country) {
        String msg = null;
        try (Session session = factory.openSession()) {

            if (country != null) {

                if (country.getCountryCode() >= 0) {
                    session.save(country);
                    session.beginTransaction().commit();
                    msg = "Country added";
                } else {
                    msg = "Country code cannot be less than zero";
                }
            } else {
                msg = "Country not Blank";
            }
        }
        return msg;
    }

    @Override
    public String updateCountry(long cid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteCountry(long cid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Country getCountryByName(String cname) {
        int status = 0;
        try (Session session = factory.openSession()) {
            if (cname != null) {
                final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                final JpaCriteriaQuery<Country> query = criteriaBuilder.createQuery(Country.class);
                final JpaRoot<Country> root = query.from(Country.class);
                final JpaCriteriaQuery<Country> countryFromDB = query.select(root).where(criteriaBuilder.equal(root.get("cname"), cname));

                final Country countryObjFromDb = session.createQuery(countryFromDB).getSingleResult();

                return  countryObjFromDb;


            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Country getCountryByNameAndCode(String cname, long countryCode) {

        try (Session session = factory.openSession()) {
            final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final JpaCriteriaQuery<Object> query = criteriaBuilder.createQuery();
            final JpaRoot<Country> root = query.from(Country.class);

            final JpaPredicate predicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("cname"), cname),
                    criteriaBuilder.equal(root.get("countryCode"), countryCode));

            query.select(root).where(predicate);

            try {
                // Attempt to get the country
                final Object countryObj = session.createQuery(query).getSingleResult();
                return (Country) countryObj;
            } catch (NoResultException e) {
                Country newCountry = new Country();
                newCountry.setCname(cname);
                newCountry.setCountryCode(countryCode);

                session.save(newCountry);

                return newCountry;
            }
        }
    }

    @Override
    public Country getCountryById(long cid) {
        try (Session session = factory.openSession()){

            if(cid > 0){

                final Country countryfromDB = session.get(Country.class, cid);
                return countryfromDB;
            }
        }
        return null;
    }

    {
//		final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		final JpaCriteriaQuery<Country> query = criteriaBuilder.createQuery(Country.class);
//		final JpaRoot<Country> root = query.from(Country.class);
//		final JpaPath<Object> cname = root.get("cname");
//		query.select(root).where(criteriaBuilder.equal(cname,country.getCname()));
//		List<Country> countries = session.createQuery(query).getResultList();
    }

}
