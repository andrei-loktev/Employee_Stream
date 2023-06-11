package com.example.employee_stream.Controller;

import com.example.employee_stream.Object.Employee;
import com.example.employee_stream.Service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("departmentId")int department){
        return departmentService.getEmployeeWithMaxSalary(department);
    }
    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("departmentId")int department){
        return departmentService.getEmployeeWithMinSalary(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getALL(@RequestParam("departmentId") int department){
        return departmentService.getAll(department);
    }
    @GetMapping("/all")
    public Map<Integer,List<Employee>> getAll(){
        return departmentService.getAll();
    }
}
