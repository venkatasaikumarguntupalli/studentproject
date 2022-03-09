package com.example.demo.dto;

import io.swagger.models.auth.In;

import javax.validation.constraints.NotBlank;

public class Studept {
    public Integer id,dept_id;

    @NotBlank(message="Student name should not be null")
    public String pname,lname,branch,dept_name,dept_hod;

    public Studept(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_hod() {
        return dept_hod;
    }

    public void setDept_hod(String dept_hod) {
        this.dept_hod = dept_hod;
    }

    public Studept(Integer id, String pname, String lname, String branch, Integer dept_id,String dept_name,String dept_hod){
        this.id=id;
        this.branch=branch;
        this.pname=pname;
        this.lname=lname;
        this.dept_id=dept_id;
        this.dept_name=dept_name;
        this.dept_hod=dept_hod;
    }
}
