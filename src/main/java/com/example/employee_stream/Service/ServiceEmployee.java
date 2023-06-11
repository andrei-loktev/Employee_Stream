package com.example.employee_stream.Service;

import com.example.employee_stream.Exception.EmployeeAlreadyAddedException;
import com.example.employee_stream.Exception.EmployeeNotFoundException;
import com.example.employee_stream.Exception.EmployeeStorageIsFullException;
import com.example.employee_stream.Object.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceEmployee {
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);
    private static final int MAX_SIZE = 5;

    public ServiceEmployee(){
        Employee employees1 = new Employee("Андрей","Петров", 1, 10000);
        Employee employees2 = new Employee("Павел","Марков", 2, 20000);
        Employee employees3 = new Employee("Настя","Классная", 1, 30000);
        Employee employees4 = new Employee("Ваня","Краснов", 2, 40000);
        Employee employees5 = new Employee("Лена","Кряк", 3, 50000);
        employees.put(employees1.getFullName(), employees1);
        employees.put(employees2.getFullName(), employees2);
        employees.put(employees3.getFullName(), employees3);
        employees.put(employees4.getFullName(), employees4);
        employees.put(employees5.getFullName(), employees5);
    }

    public Employee add(String firstName, String lastName, int department, double salary) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(employeeToAdd.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employeeToAdd.getFullName(), employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName, int department, double salary) {
        Employee employeeToRemove = new Employee(firstName, lastName, department, salary);
        if (!employees.containsKey(employeeToRemove.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(employeeToRemove.getFullName());

    }

    public Employee find(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (!employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }
}
