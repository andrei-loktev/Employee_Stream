package com.example.employee_stream.service;

import com.example.employee_stream.Exception.EmployeeAlreadyAddedException;
import com.example.employee_stream.Exception.EmployeeNotFoundException;
import com.example.employee_stream.Exception.EmployeeStorageIsFullException;
import com.example.employee_stream.Object.Employee;
import com.example.employee_stream.Service.ServiceEmployee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    ServiceEmployee serviceEmployee = new ServiceEmployee();

    //  проверка на переполнение
    @Test
    void whenFullThrowException() {
        Employee employees1 = new Employee("Андрей", "Петров", 1, 10000);
        Employee employees2 = new Employee("Павел", "Марков", 2, 20000);
        Employee employees3 = new Employee("Настя", "Классная", 1, 30000);
        Employee employees4 = new Employee("Ваня", "Краснов", 2, 40000);
        Employee employees5 = new Employee("Петя", "Жуков", 3, 50000);
        serviceEmployee.add(employees1);
        serviceEmployee.add(employees2);
        serviceEmployee.add(employees3);
        serviceEmployee.add(employees4);
        serviceEmployee.add(employees5);

        assertThrows(EmployeeStorageIsFullException.class,
                () -> serviceEmployee.add(new Employee("Марк", "Марков", 1, 5000)));
    }

    //  проверка на 2х одинаковых сотрудников
    @Test
    void whenEqualThrowException() {
        Employee employee = new Employee("Андрей", "Петров", 1, 10000);
        serviceEmployee.add(employee);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> serviceEmployee.add(employee));
    }

    //   проверка на добавление сотрудника
    @Test
    void addPositive() {
        Employee employee = new Employee("Андрей", "Петров", 1, 10000);
        serviceEmployee.add(employee);
        assertTrue(serviceEmployee.getAll().contains(employee));
    }

    //    поиск по имени и фамилии
    @Test
    void findPositive() {
        Employee employee = new Employee("Андрей", "Петров", 1, 10000);
        serviceEmployee.add(employee);
        Employee actual = serviceEmployee.find("Андрей", "Петров", 2, 2000);
        assertNotNull(employee);
        assertEquals(actual, employee);
    }

    @Test
    void findNegative() {
        Employee employee = new Employee("Андрей", "Петров", 1, 10000);
        serviceEmployee.add(employee);
        assertThrows(EmployeeNotFoundException.class,
                () -> serviceEmployee.find("Оля", "Вок", 2, 35000));
    }

    //    удаление существующего сотрудника
    @Test
    void removePositive() {
        Employee employee = new Employee("Андрей", "Петров", 1, 10000);
        serviceEmployee.add(employee);
        serviceEmployee.remove("Андрей", "Петров", 1, 10000);
        assertFalse(serviceEmployee.getAll().contains(employee));
    }

    //    удаление не существующего сотрудника
    @Test
    void removeNegative() {
        Employee employee = new Employee("Андрей", "Петров", 1, 10000);
        serviceEmployee.add(employee);
        assertThrows(EmployeeNotFoundException.class,
                () -> serviceEmployee.remove("Оля", "Вок", 2, 35000));
    }

}
