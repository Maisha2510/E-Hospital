package com.example.ehospital;

public class DepartmentTableModel {
    private String departmentName, description, status, departmentId;
    private int serialNo;

    public DepartmentTableModel(String departmentName, String description, String status, String departmentId, int serialNo) {
        this.departmentName = departmentName;
        this.description = description;
        this.status = status;
        this.departmentId = departmentId;
        this.serialNo = serialNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }
}
