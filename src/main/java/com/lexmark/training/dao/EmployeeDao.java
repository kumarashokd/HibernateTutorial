package com.lexmark.training.dao;

import com.lexmark.training.utils.HibernateUtil;
import com.lexmark.training.entiry.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao {
    public void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist(employee);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employee> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    public List<Employee> getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee where name = :name", Employee.class)
                    .setParameter("name", name)
                    .list();
        }
    }

    public void delete(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.remove(employee);
            return;
        }
    }
}
