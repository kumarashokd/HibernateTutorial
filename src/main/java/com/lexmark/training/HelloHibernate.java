package com.lexmark.training;

import com.lexmark.training.dao.EmployeeDao;
import com.lexmark.training.entity.Employee;
import com.lexmark.training.entity.inheritance.MojaPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;

import java.util.List;

public class HelloHibernate {

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-test-hibernate");
        return emf.createEntityManager();
    }

    public static void main(String args[]) {
        System.out.println("Hello Hibernate");
        EmployeeDao employeeDao = new EmployeeDao();
        //1. Transient State
        Employee employee = new Employee("Jayanta", 10);
        employeeDao.save(employee);

        List<Employee> employeeList = employeeDao.getAll();
        employeeList.forEach(e ->
                System.out.println(e.toString())
        );
        System.out.println("End");
    }


    public static void lockingPessimistic() {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Employee emp = new Employee("Ashok", 20);
        em.persist(emp);
        em.getTransaction().commit();

        em.getTransaction().begin();
        List<Employee> employees = em.createQuery("from Employee", Employee.class)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();
        try {
            Thread.sleep(30000);
            employees.forEach(e ->
                    System.out.println(e.toString())
            );
            em.getTransaction().commit();
        } catch (InterruptedException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public static void lockingOptimistic() {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Employee employee = new Employee("Ashok", 20);
        em.persist(employee);
        em.getTransaction().commit();

        List<Employee> employeeList = em.createQuery("from Employee where name = :name", Employee.class)
                .setParameter("name", "Ashok")
                .getResultList();
        employeeList.forEach(e ->
                System.out.println(e.toString())
        );

        Employee e = employeeList.get(0);
        e.setSalary(5000);
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }


    public static void inheritance() {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        MojaPrinter mp = new MojaPrinter("Mahi", "X500", "LXK-1.0.0", "Android-1.0.1");

        em.persist(mp);
        em.getTransaction().commit();
    }

}
