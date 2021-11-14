package net.sriram.springbootpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sriram.springbootpost.model.Employee;




@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
