package com.mrigor.tasks.department.dao;

import com.mrigor.tasks.department.model.Department;
import com.mrigor.tasks.department.to.DepartmentWithAverageSalary;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by igor on 011 11.05.17.
 */
public interface DepartmentDao {


    /**
     * update or create new record of department in database
     * @param department
     * @return updated or created entity
     */
    @Insert("INSERT INTO departments (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true)
    void insert(Department department);



    /**
     * update or create new record of department in database
     * @param department
     * @return updated or created entity
     */
    @Update("UPDATE departments SET name=#{name} WHERE id=#{id}")
    void update(Department department);

    @Delete("DELETE FROM DEPARTMENTS WHERE id=#{id}")
    boolean delete(int id);

    /**
     * get record of department by id from database
     * @param id entity identifier
     * @return entity or null if not found
     */
    @Select("SELECT * FROM departments WHERE  id = #{id}")
    Department get(int id);

    /**
     * get all departments from database
     * @return List all entities
     */
    @Select("SELECT * FROM departments  ORDER BY name")
    List<Department> getAll();

    /**
     * get all entity with information about average salary
     * @return List all entities
     */
    @Select(
            "SELECT d.ID, d.NAME, AVG(e.SALARY) AS averagesalary " +
                    "FROM EMPLOYEES e " +
                    "RIGHT JOIN DEPARTMENTS d ON e.DEPARTMENT_ID = d.ID " +
                    "GROUP BY d.ID " +
                    "ORDER  BY d.NAME"
    )
    //@ResultMap("com.mrigor.tasks.department.dao.DepartmentDao.DepartmentWithAverageSalaryResult")
    List<DepartmentWithAverageSalary> getAllWithAvgSalary();
}
