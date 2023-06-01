package com.lexmark.training;

import com.lexmark.training.dao.EmployeeDao;
import com.lexmark.training.entiry.Employee;

import java.util.List;

public class HelloHibernate {
    public static void main(String args[]) {
        System.out.println("Hello Hibernate");
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = new Employee("Ramesh", 5000);
        employeeDao.save(employee);

        List<Employee> employeeList = employeeDao.getAll();
        employeeList.forEach(e ->
                System.out.println(e.toString())
        );
    }
}
