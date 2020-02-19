package io.twg.payroll.controller;

import io.twg.payroll.PayrollApplication;
import io.twg.payroll.entity.Employee;
import io.twg.payroll.repository.IEmployeeRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit. Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PayrollApplication.class, webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @After
    public void cleanup()
    {
        employeeRepository.deleteAll();
    }

    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        System.out.println(getRootUrl());
        ResponseEntity<String> response = testRestTemplate.exchange( getRootUrl() +"/employees/",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());

    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("emp 1");
        employee.setProvince("ON");
        employee.setSalary(new BigDecimal("1000000"));
        employee.setRole("manager");

        employeeRepository.save(employee);

        ResponseEntity<Employee> postResponse = testRestTemplate.postForEntity(getRootUrl() + "/employees/", employee, Employee.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(employee.getName(), postResponse.getBody().getName());

    }

    private String getRootUrl() {
        return "http://localhost:" + port;
    }


}
