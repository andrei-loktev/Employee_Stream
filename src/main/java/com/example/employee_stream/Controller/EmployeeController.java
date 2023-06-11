package com.example.employee_stream.Controller;

import com.example.employee_stream.Object.Employee;
import com.example.employee_stream.Service.ServiceEmployee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final ServiceEmployee service;

    public EmployeeController(ServiceEmployee service) {
        this.service = service;
    }

    @GetMapping("add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                        int department, double salary) {
        return service.add(firstName, lastName, department, salary);
    }

    @GetMapping("remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                           int department, double salary) {
        return service.remove(firstName, lastName, department, salary);
    }

    @GetMapping("find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                         int department, double salary) {
        return service.find(firstName, lastName, department, salary);
    }

    @GetMapping("all")
    public Collection<Employee> all() {
        return service.getAll();
    }
}
