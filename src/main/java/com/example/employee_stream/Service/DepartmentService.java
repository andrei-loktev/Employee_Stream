package com.example.employee_stream.Service;

import com.example.employee_stream.Exception.EmployeeNotFoundException;
import com.example.employee_stream.Object.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final ServiceEmployee serviceEmployee;

    public DepartmentService(ServiceEmployee serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public Employee getEmployeeWithMaxSalary(int department) {
        return serviceEmployee.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public Employee getEmployeeWithMinSalary(int department) {
        return serviceEmployee.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public List<Employee> getAll(int department){
        return serviceEmployee.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
    }
    public Map<Integer,List<Employee>> getAll(){
        return serviceEmployee.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}

