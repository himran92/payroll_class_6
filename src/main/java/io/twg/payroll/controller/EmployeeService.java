package io.twg.payroll.controller;

import io.twg.payroll.entity.Employee;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmployeeService {

    public BigDecimal calculateTaxes(Employee employee){

        BigDecimal taxBracket = new BigDecimal(0);
        if(employee.getProvince().equals("Ontario")){
            taxBracket =  new BigDecimal(13);
        } else if(employee.getProvince().equals("Quebec")) {
            taxBracket = new BigDecimal(31);
        }

        return employee.getSalary().multiply(taxBracket).divide(new BigDecimal(100));
    }
}
