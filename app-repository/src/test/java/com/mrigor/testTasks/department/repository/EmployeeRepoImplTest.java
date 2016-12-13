package com.mrigor.testTasks.department.repository;

import com.mrigor.testTasks.department.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;


import static com.mrigor.testTasks.department.repository.DepTestData.DEP1_ID;
import static com.mrigor.testTasks.department.repository.EmployeeTestData.*;
import static java.time.LocalDate.of;
import static org.junit.Assert.*;

/**
 * Created by Игорь on 10.12.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql")
public class EmployeeRepoImplTest {

    @Autowired
    EmployeeRepo repository;

    @Test
    public void update() throws Exception {
        Employee updateEmpl=getUpdated();
        updateEmpl.setDepartmentId(DEP1_ID);
        repository.save(updateEmpl);
        MATCHER.assertCollectionEquals(Arrays.asList(EMPL2,EMPL3,updateEmpl), repository.getByDep(DEP1_ID));
    }

    @Test
    public void create() throws Exception {
        Employee createEmpl=getCreated();
        createEmpl.setDepartmentId(DEP1_ID);
        repository.save(createEmpl);
        MATCHER.assertCollectionEquals(Arrays.asList(EMPL1,createEmpl,EMPL2,EMPL3), repository.getByDep(DEP1_ID));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createException() throws Exception {
        Employee createEmpl=getCreated();
        createEmpl.setDepartmentId(8);
        repository.save(createEmpl);
        MATCHER.assertCollectionEquals(Arrays.asList(EMPL1,createEmpl,EMPL2,EMPL3), repository.getByDep(DEP1_ID));
    }

    @Test
    public void delete() throws Exception {
        repository.delete(EMPL1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(EMPL2,EMPL3), repository.getByDep(DEP1_ID));
    }

    @Test
    public void get() throws Exception {
        Employee empl=repository.get(EMPL1_ID);
        MATCHER.assertEquals(empl,EMPL1);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(EMPL_ALL, repository.getAll());

    }

    @Test
    public void getByDep() throws Exception {
        MATCHER.assertCollectionEquals(EMPL_D1, repository.getByDep(DEP1_ID));
    }

    @Test
    public void getBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(EMPL5,EMPL4), repository.getBetweenDates(of(1993,1,1),null));
    }
    @Test
    public void getAllWithAvgSalary() throws Exception{

    }

}