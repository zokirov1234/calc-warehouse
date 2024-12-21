package com.company.repository;

import com.company.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Modifying
    @Query("update Department set name = ?1 where id = ?2")
    void updateByName(String name, int id);

    @Query("select department from Department department where department.state = true order by department.name desc")
    List<Department> getList();

}
