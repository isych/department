package com.mrigor.tasks.department.dao;

import com.mrigor.tasks.department.model.Department;
import com.mrigor.tasks.department.to.DepartmentWithAverageSalary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by igor on 011 11.05.17.
 */
@Repository
public interface DepartmentDao {

 String INSERT_SQL="INSERT INTO departments (name) VALUES (#{name})";
 String UPDATE_SQL="UPDATE departments SET name=#{name} WHERE id=#{id}";
 String DELETE_SQL="DELETE FROM DEPARTMENTS WHERE id=#{id}";
 String SELECT_BY_ID="SELECT * FROM departments WHERE  id = #{id}";
 String SELECT_ALL="SELECT * FROM departments  ORDER BY name";

 String SELECT_WITH_AVG_SALARY= "SELECT d.ID, d.NAME, AVG(e.SALARY) AS averagesalary " +
                                      "FROM EMPLOYEES e " +
                                      "RIGHT JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.ID " +
                                      "GROUP BY d.ID " +
                                      "ORDER  BY d.NAME";

    @Insert(INSERT_SQL)
    @Options(useGeneratedKeys = true)
    int insert(Department department);


    @Update(UPDATE_SQL)
    int update(Department department);


    @Delete(DELETE_SQL)
    boolean delete(int id);


    @Select(SELECT_BY_ID)
    @ResultMap("com.mrigor.tasks.department.dao.DepartmentDao.DepartmentResult")
    Department getWithEmployees(int id);


    @Select(SELECT_BY_ID)
    Department get(int id);


    @Select(SELECT_ALL)
    List<Department> getAll();


    @Select(SELECT_WITH_AVG_SALARY)
    List<DepartmentWithAverageSalary> getAllWithAvgSalary();
}
