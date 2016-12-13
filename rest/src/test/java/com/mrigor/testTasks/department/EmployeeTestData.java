package com.mrigor.testTasks.department;

import com.mrigor.testTasks.department.model.Employee;
import com.mrigor.testTasks.department.matcher.ModelMatcher;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;

/**
 * Created by Игорь on 10.12.2016.
 */
public class EmployeeTestData {
    public static final ModelMatcher<Employee> MATCHER = new ModelMatcher<>();
/*    ('Ivanov Ivan', '1980-05-17', 400, 100000),
            ('Petrov Petr', '1992-12-31', 250, 100000),
            ('Sidorov Anton', '1992-11-05', 210, 100000),
            ('Jon W', '1993-01-01', 250, 100001),
            ('Bob A', '1993-06-12', 300, 100001*/


    public static final int EMPL1_ID = 100002;
    public static final int EMPL2_ID = 100003;
    public static final int EMPL3_ID = 100004;
    public static final int EMPL4_ID = 100005;
    public static final int EMPL5_ID = 100006;

/*  public Employee(Integer id,String fullName, LocalDate birthDay, int salary) */
    public static final Employee EMPL1= new Employee(EMPL1_ID,"Ivanov Ivan", of(1980,5,17),400);
    public static final Employee EMPL2= new Employee(EMPL2_ID,"Petrov Petr", of(1992,12,31),250);
    public static final Employee EMPL3= new Employee(EMPL3_ID,"Sidorov Anton", of(1992,11,5),210);
    public static final Employee EMPL4= new Employee(EMPL4_ID,"Jon W", of(1993,1,1),250);
    public static final Employee EMPL5= new Employee(EMPL5_ID,"Bob A", of(1993,6,12),300);

    public static final List<Employee> EMPL_D1 = Arrays.asList(EMPL1, EMPL2,EMPL3);
    public static final List<Employee> EMPL_D2 = Arrays.asList(EMPL5, EMPL4);
    public static final List<Employee> EMPL_ALL = Arrays.asList(EMPL5, EMPL1,EMPL4,EMPL2,EMPL3);
    public static Employee getCreated() {
        return new Employee("New employee",of(1960,10,10),100);
    }

    public static Employee getUpdated() {
        return new Employee(EMPL1_ID, "Update employee",EMPL1.getBirthDay(),300);
    }

}