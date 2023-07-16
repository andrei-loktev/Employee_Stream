package com.example.employee_stream.service;

import com.example.employee_stream.Object.Employee;
import com.example.employee_stream.Service.DepartmentService;
import com.example.employee_stream.Service.ServiceEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DepartmentServiceTest {
    private static final Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("Андрей", "Петров", 1, 10000),
            new Employee("Павел", "Марков", 2, 20000),
            new Employee("Настя", "Классная", 1, 30000),
            new Employee("Ваня", "Краснов", 2, 40000),
            new Employee("Петя", "Жуков", 3, 50000)
    );
    @Mock
    ServiceEmployee serviceEmployee;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void inThisClass() {
        when(serviceEmployee.getAll()).thenReturn(EMPLOYEES);
    }

    @Test
    void minSalary() {
        double min = departmentService.getEmployeeWithMinSalary(1);
        assertEquals(10000, min);
    }

    @Test
    void maxSalary() {
        double max = departmentService.getEmployeeWithMaxSalary(2);
        assertEquals(40000, max);
    }

    @Test
    void sumSalary() {
        double sum = departmentService.getEmployeeSumSalary(2);
        assertEquals(60000, sum);
    }

    //    проверка списка сотрудников по отделу
    @Test
    void getAllByDepartment() {
        List<Employee> actual = departmentService.getAll(3);
        List<Employee> expected = Collections.singletonList(
                new Employee("Петя", "Жуков", 3, 50000));
        assertIterableEquals(expected, actual);
    }
    
    @Test
    void getAll(){
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        Employee employee = new Employee("Андрей", "Петров", 1, 10000);
        assertTrue(actual.get(1).contains(employee));
        assertFalse(actual.get(2).contains(employee));
        assertEquals(3,actual.keySet().size());
    }
}
