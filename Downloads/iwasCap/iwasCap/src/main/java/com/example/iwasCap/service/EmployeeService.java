package com.example.iwasCap.service;

import com.example.iwasCap.model.Employee;
import com.example.iwasCap.model.Skill;
import com.example.iwasCap.model.LeaveRequest;
import com.example.iwasCap.repository.EmployeeRepository;
import com.example.iwasCap.repository.SkillRepository;
import com.example.iwasCap.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new employee.
     */
    public Employee registerEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword())); // Encrypt password
        return employeeRepository.save(employee);
    }

    /**
     * Authenticate employee login.
     */
    public Optional<Employee> loginEmployee(String email, String password) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        return employee.filter(emp -> passwordEncoder.matches(password, emp.getPassword()));
    }

    /**
     * Get all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Get an employee by ID.
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Update employee details.
     */
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setAvailability(updatedEmployee.isAvailability());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    /**
     * Delete an employee.
     */
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
 * Add skills to an employee.
 */
public Employee addSkillsToEmployee(Long employeeId, Set<Long> skillIds) {
    return employeeRepository.findById(employeeId).map(employee -> {
        Set<Skill> skills = new HashSet<>(skillRepository.findAllById(skillIds)); // Convert to Set
        employee.getSkills().addAll(skills);
        return employeeRepository.save(employee);
    }).orElse(null);
}


    /**
     * Employee applies for leave.
     */
    public LeaveRequest applyLeave(Long employeeId, LeaveRequest leaveRequest) {
        return employeeRepository.findById(employeeId).map(employee -> {
            leaveRequest.setEmployee(employee);
            return leaveRequestRepository.save(leaveRequest);
        }).orElse(null);
    }

    /**
     * Get all leave requests of an employee.
     */
    public List<LeaveRequest> getEmployeeLeaves(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }
}
