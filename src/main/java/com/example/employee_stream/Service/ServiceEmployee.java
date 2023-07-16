package com.example.employee_stream.Service;

import com.example.employee_stream.Exception.EmployeeAlreadyAddedException;
import com.example.employee_stream.Exception.EmployeeNotFoundException;
import com.example.employee_stream.Exception.EmployeeStorageIsFullException;
import com.example.employee_stream.Exception.InvalidDataException;
import com.example.employee_stream.Object.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceEmployee {
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);
    private static final int MAX_SIZE = 5;

    public Collection<Employee> getAll() {
        return employees.values();
    }

//    public ServiceEmployee() {
//        Employee employees1 = new Employee("Андрей", "Петров", 1, 10000);
//        Employee employees2 = new Employee("Павел", "Марков", 2, 20000);
//        Employee employees3 = new Employee("Настя", "Классная", 1, 30000);
//        Employee employees4 = new Employee("Ваня", "Краснов", 2, 40000);
//        employees.put(createKey(employees1), employees1);
//        employees.put(createKey(employees2), employees2);
//        employees.put(createKey(employees3), employees3);
//        employees.put(createKey(employees4), employees4);
//    }

    public Employee add(Employee employee) {
        if (!StringUtils.isAlpha(employee.getFirstName()) ||
                !StringUtils.isAlpha(employee.getLastName())) {
            throw new InvalidDataException();
        }
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        correctData(employee);
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName, int department, double salary) {
        Employee employeeToRemove = new Employee(firstName, lastName, department, salary);
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(createKey(firstName, lastName));
        return employeeToRemove;
    }

    public Employee find(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(createKey(firstName, lastName));
    }

    private static void correctData(Employee employee) {
        String correctFirstName = StringUtils.capitalize(employee.getFirstName().toLowerCase());
        employee.setFirstName(correctFirstName);
        String correctLastName = StringUtils.capitalize(employee.getLastName().toLowerCase());
        employee.setLastName(correctLastName);
    }

    private String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    private String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }
}


