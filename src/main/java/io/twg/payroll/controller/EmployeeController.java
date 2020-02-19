package io.twg.payroll.controller;

import io.twg.payroll.controller.exceptions.BadRequestException;
import io.twg.payroll.controller.exceptions.NotFoundException;
import io.twg.payroll.entity.Employee;
import io.twg.payroll.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeRepository empRepository;


    @Autowired
    private EmployeeService employeeService;

    @Value("${test.message.value.annotation:Value not found, showing default message}")
    private String testMessage;

    @GetMapping("/testProperty")
    public String getTestProperty(){
        return testMessage;
    }

    @GetMapping("/")
    public List<Employee> getEmployees(){
        return (List<Employee>)empRepository.findAll();
    }


    @GetMapping("/calculateTaxes/{id}")
    public BigDecimal calculateTaxes(@PathVariable long id){

        Employee employee = empRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
        return employeeService.calculateTaxes(employee);

    }


    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id){
        return empRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @PostMapping("/")
    public Employee postEmployee(@Valid @RequestBody Employee newEmp){
        return empRepository.save(newEmp);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        empRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
        empRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Employee updateById(@PathVariable long id,  @Valid @RequestBody Employee emp){
        if (id != emp.getId()) {
            throw new BadRequestException("Id mismatch");
        }
        Employee employee = empRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
        return empRepository.save(emp);
    }
}
