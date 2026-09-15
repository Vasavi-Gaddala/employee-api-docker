package com.example.employee_api.service;

import com.example.employee_api.Model.Employee;
import com.example.employee_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    // POST
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // PUT
    public Employee updateEmployee(int id, Employee employee) {

        Optional<Employee> existingEmployee =
                employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {

            Employee existing = existingEmployee.get();

            existing.setName(employee.getName());
            existing.setSalary(employee.getSalary());

            return employeeRepository.save(existing);
        }

        return null;
    }

    // DELETE
    public String deleteEmployee(int id) {

        if (employeeRepository.existsById(id)) {

            employeeRepository.deleteById(id);

            return "Employee " + id + " deleted successfully";
        }

        return "Employee " + id + " not found";
    }
}