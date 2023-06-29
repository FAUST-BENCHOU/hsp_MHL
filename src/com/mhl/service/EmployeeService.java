package com.mhl.service;

import com.mhl.dao.EmployeeDao;
import com.mhl.domain.Employee;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/27 21:23
 */
public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public Employee getEmployeeByIdAndPwd(String empId,String pwd){
        return employeeDao.querySingle("select * from employee where empId=? and pwd=md5(?)",Employee.class,empId,pwd);
    }
}
