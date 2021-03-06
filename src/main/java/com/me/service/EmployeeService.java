package com.me.service;

import com.me.Utils.Md5Utils;
import com.me.dao.EmployeeDao;
import com.me.dto.SearchEmpDto;
import com.me.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeDao employeeDao;
    
    private Employee loginUser;

    public Employee getLoginUser() {
        return loginUser;
    }

    public int login(String username, String password) {
        int result = 3;//表示登录失败
        String md5Password = Md5Utils.getMd5(username,password);
        Employee loginEmp = employeeDao.login(username,md5Password);
        if (loginEmp == null) {
            return result;
        }else{
            this.loginUser = loginEmp;
            return loginEmp.getStatus();
        }
    }

    public Employee getEmpByEmpId(int empId){

        return employeeDao.getEmpByEmpId(empId);

    }

    public   int reg(Employee e){

        String username = e.getUsername();
        String password = e.getPassword();
        String md5Password = Md5Utils.getMd5(username,password);

        e.setPassword(md5Password);
        return employeeDao.reg(e);
    }

    public  List<Employee> getUnApproveaccount(){
        return employeeDao.getUnApproveaccount();
    }

    public  int updateEmpStatusById(int id, int status){

        return employeeDao.updateEmpStatusById(id,status);
    }

    public  List<Employee> searchEmp(SearchEmpDto searchEmpDto) {
        return employeeDao.searchEmp(searchEmpDto);
    }

    public  int getCount(SearchEmpDto searchEmpDto) {
        return employeeDao.getCount(searchEmpDto);
    }

    public  List<Employee> getEmpByDepId(int depId){
        return employeeDao.getEmpByDepId(depId);
    }

    public   String getOddPsswordByEmpId(int empId){
        return employeeDao.getOddPsswordByEmpId(empId);
    }

    public   int updateOddPsswordByEmpId(int empId,String password){
        return  employeeDao.updateOddPsswordByEmpId(empId,password);
    }
}
