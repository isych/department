package com.mrigor.tasks.department.dao;

import com.mrigor.tasks.department.model.Employee;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by igor on 011 11.05.17.
 */
public interface EmployeeDao {

    /**
     * update or create new record of employee in database
     *
     * @param employee
     * @return updated or created entity
     */
    @Insert(value = "INSERT INTO employees (fullName,birthday,salary,department_id) VALUES (#{fullName},#{birthDay},#{salary},#{department.id})")
    // UPDATE EMPLOYEES SET FULLNAME=:fullName, BIRTHDAY=:birthday, SALARY=:salary WHERE id=:id
    @Options(useGeneratedKeys = true)
    @ResultMap("com.mrigor.tasks.department.dao.EmployeeDao.EmployeeResult")
    void insert(Employee employee);

    // @Insert(value = "INSERT INTO employees (fullName,birthday,salary,department_id) VALUES (#{fullName},#{birthday},#{salary},#{departmentId})")
    @Update("UPDATE EMPLOYEES SET FULLNAME=#{fullName}, BIRTHDAY=#{birthDay}, SALARY=#{salary} WHERE id=#{id}")
    // UPDATE EMPLOYEES SET FULLNAME=:fullName, BIRTHDAY=:birthday, SALARY=:salary WHERE id=id
    @ResultMap("com.mrigor.tasks.department.dao.EmployeeDao.EmployeeResult")
    void update(Employee employee);

    /**
     * delete record of employee by id from database
     *
     * @param id entity identifier
     * @return false if not found
     */
    @Delete("DELETE FROM employees WHERE id=#{id}")
    @ResultMap("com.mrigor.tasks.department.dao.EmployeeDao.EmployeeResult")
    boolean delete(int id);

    /**
     * get record of employee by id from database
     *
     * @param id entity identifier
     * @return entity or null if not found
     */
    @Select("SELECT * FROM employees WHERE  id=#{id}")
    @ResultMap("com.mrigor.tasks.department.dao.EmployeeDao.EmployeeResult")
/*    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="fullname", property="fullName"),
            @Result(column="birthday", property="birthDay"),
            @Result(column="salary", property="salary"),
            @Result(property="department", column="department_id",
                    one=@One(select="com.mrigor.tasks.department.dao.DepartmentDao.get"))
    })*/
    Employee get(int id);

    /**
     * get all employees from database
     *
     * @return List all entities
     */
    @Select("SELECT * FROM employees  ORDER BY FULLNAME")
    @ResultMap("com.mrigor.tasks.department.dao.EmployeeDao.EmployeeResult")

    List<Employee> getAll();


    @Select("SELECT * FROM EMPLOYEES WHERE EMPLOYEES.DEPARTMENT_ID=#{departmentId} ORDER BY FULLNAME")
    List<Employee> getByDep2(int departmentId);
    /**
     * get all employees by department from database
     *
     * @param departmentId identifier of department
     * @return employee's list, or empty list if missing
     */
    @Select("SELECT * FROM EMPLOYEES WHERE EMPLOYEES.DEPARTMENT_ID=#{departmentId} ORDER BY FULLNAME")
    @ResultMap("com.mrigor.tasks.department.dao.EmployeeDao.EmployeeResult")
    List<Employee> getByDepWithDepartment(int departmentId);

    /**
     * get all employees by filter from database
     * parameters of filter
     *
     * @param from         day of birth
     * @param to           day of birth
     * @param departmentId identifier of department
     * @return employee's list or empty list if missing
     */
    @Select("SELECT * FROM EMPLOYEES WHERE (BIRTHDAY BETWEEN  #{from} AND #{to})  ORDER BY FULLNAME")
    //@Select("SELECT * FROM EMPLOYEES WHERE birthday=#{from}  ORDER BY FULLNAME")
    //@Select("SELECT * FROM EMPLOYEES WHERE birthday BETWEEN  #{from} and #{from}  ORDER BY FULLNAME")
    //@Select("SELECT * FROM EMPLOYEES WHERE (BIRTHDAY BETWEEN WHERE birthday= #{to,jdbcType = DATE}")
    List<Employee> getFiltered(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("departmentid") Integer departmentId);

}