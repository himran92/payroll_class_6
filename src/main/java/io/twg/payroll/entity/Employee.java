package io.twg.payroll.entity;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotBlank(message = "{validation.name.field.empty}")
    private String name;

    @Column
    @NotBlank(message = "Role is mandatory")
    private String role;

    @Column
    @NotNull
    @Positive
    @DecimalMin("1.00")
    private BigDecimal salary;

    @Column
    @NotBlank(message = "Province is mandatory")
    private String province;


    public Employee() {
    }

    public Employee(String name, String role, BigDecimal salary, String province) {
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
